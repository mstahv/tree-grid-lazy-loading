package com.example.demo;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.TreeTable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Route
public class MainView extends VerticalLayout {
    public MainView(EmployeeService service) {
        add(new H1("Hello world"));

        TreeTable<DirectReportsDto> treeTable = new TreeTable<>();
        treeTable.addHierarchyColumn(empl -> empl.getFirstName() + " " + empl.getLastName())
                .setHeader("Name")
                .setFlexGrow(4)
                .setResizable(true);
        ;
        treeTable.addColumn(DirectReportsDto::getTitle).setHeader("Title");
        treeTable.addColumn(DirectReportsDto::getDirectReports).setHeader("Direct reports");
        treeTable.addColumn(DirectReportsDto::getEmployeeId).setHeader("EmId");
        treeTable.addColumn(DirectReportsDto::getManagerId).setHeader("ManId");
        treeTable.addColumn(DirectReportsDto::getPath).setHeader("Path");
        treeTable.addColumn(DirectReportsDto::getLevel).setHeader("Level");
        treeTable.setLevelModel(DirectReportsDto::getLevel);

        // You need to be able to somehow let the TreeTable know if the node is a leaf or not
        // here we assume that if the title does not contain any of these words, it is a leaf
        treeTable.setLeafModel(empl ->
                !Stream.of("officer", "president", "manager")
                        .anyMatch(s -> empl.getTitle().toLowerCase().contains(s)));
        // or (if you e.g. have number of subnodes in your query)
        treeTable.setLeafModel(empl -> empl.getDirectReports() == 0);

        // For proper lazy loading of large tree structure, open/closed
        // state of subtrees needs to be passed to the backend.
        // In this example, we have all nodes open by default and
        // if you close a node, it will be added to the closedSubtrees set
        final Set<Integer> closedSubtrees = new HashSet<>();
        TreeTable.OpenModel<DirectReportsDto> openModel = new TreeTable.OpenModel<DirectReportsDto>() {

            @Override
            public boolean isOpen(DirectReportsDto dto) {
                return !closedSubtrees.contains(dto.getEmployeeId());
            }

            @Override
            public void setOpen(DirectReportsDto dto, boolean b) {
                if(b) {
                    closedSubtrees.remove(dto.getEmployeeId());
                } else {
                    closedSubtrees.add(dto.getEmployeeId());
                }
            }
        };
        treeTable.setOpenModel(openModel);

        treeTable.setItems(q -> {
            System.out.println("query for: offset" + q.getOffset() + " l" + q.getLimit() + " closed subtrees: " + closedSubtrees);
            return service.findAll(q.getOffset(), q.getLimit(), closedSubtrees).stream();
        });

        add(treeTable);


    }

}
