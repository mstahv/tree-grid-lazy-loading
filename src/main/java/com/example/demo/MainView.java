package com.example.demo;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.firitin.components.TreeTable;

import java.util.HashSet;
import java.util.Set;

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
        treeTable.addColumn(DirectReportsDto::isLeaf).setHeader("Is Leif :-)");
        treeTable.addColumn(DirectReportsDto::getPath).setHeader("Path");
        treeTable.addColumn(DirectReportsDto::getLevel).setHeader("Level");
        treeTable.setLevelModel(DirectReportsDto::getLevel);

        // This is kind of handy if read-only nodes, but this is query specific in Oracle so does not work if nodes are skipped in the query
        // TODO figure our an API to TreeTable to disable user expanding/collapsing. Would simplify usage quite a while
        treeTable.setLeafModel(empl -> empl.isLeaf());
        // Use number of direct reports (sub query) to determine if node is leaf, this words even if subtree is ignored in the query
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
