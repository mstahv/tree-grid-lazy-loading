package com.example.demo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.TreeTable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Route
public class MainView extends VerticalLayout {
    public MainView(EmployeeService service) {
        add(new H1("Tree structure from PostgreSQL using CTE"));

        TreeTable<DirectReportsDto> treeTable = new TreeTable<>();
        treeTable.addHierarchyColumn(empl -> empl.getFirstName() + " " + empl.getLastName())
                .setHeader("Name")
                .setFlexGrow(4)
                .setResizable(true);
        ;
        treeTable.addColumn(DirectReportsDto::getTitle).setHeader("Title");
        treeTable.addColumn(DirectReportsDto::getDirectReports).setHeader("Direct reports");

        // Meta info column for demo purposes (and undertanding who things work)
        Collection<Grid.Column> metaColumns = new HashSet<>();
        metaColumns.add(treeTable.addColumn(DirectReportsDto::getEmployeeId).setHeader("EmId"));
        metaColumns.add(treeTable.addColumn(DirectReportsDto::getManagerId).setHeader("ManId"));
        metaColumns.add(treeTable.addColumn(DirectReportsDto::getPath).setHeader("Path"));
        metaColumns.add(treeTable.addColumn(DirectReportsDto::getLevel).setHeader("Level"));

        metaColumns.forEach(c -> c.getStyle().setColor("gray"));

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
        // In this example, we have all nodes open by default.
        // Closed nodes are passed to the queries to limit the result set

        var openModel = new TreeTable.OpenByDefault<DirectReportsDto>();
        treeTable.setOpenModel(openModel);

        treeTable.setItems(q -> {
            System.out.println("query for: offset" + q.getOffset() + " l" + q.getLimit() + " closed subtrees: " + openModel.getClosed());
            return service.findAll(q.getOffset(), q.getLimit(), openModel.getClosed()).stream();
        });

        add(treeTable);
        
    }

}
