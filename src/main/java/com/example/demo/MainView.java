package com.example.demo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.TreeTable;

import java.util.Collection;
import java.util.HashSet;

@Route
public class MainView extends VerticalLayout {
    public MainView(EmployeeService service) {
        add(new H1("Lazy loading hierarchical data from Oracle DB"));

        TreeTable<DirectReportsDto> treeTable = new TreeTable<>();
        treeTable.addHierarchyColumn(empl -> empl.getFirstName() + " " + empl.getLastName())
                .setHeader("Name")
                .setFlexGrow(4)
                .setResizable(true);

        treeTable.addColumn(DirectReportsDto::getTitle).setHeader("Title");
        treeTable.addColumn(DirectReportsDto::getDirectReports).setHeader("Direct reports");

        Collection<Grid.Column> metaInfoColumns = new HashSet<>();
        metaInfoColumns.add(treeTable.addColumn(DirectReportsDto::getEmployeeId).setHeader("EmId"));
        metaInfoColumns.add(treeTable.addColumn(DirectReportsDto::getManagerId).setHeader("ManId"));
        metaInfoColumns.add(treeTable.addColumn(DirectReportsDto::isLeaf).setHeader("Is Leif :-)"));
        metaInfoColumns.add(treeTable.addColumn(DirectReportsDto::getPath).setHeader("Path"));
        metaInfoColumns.add(treeTable.addColumn(DirectReportsDto::getLevel).setHeader("Level"));
        metaInfoColumns.forEach(col -> {
            // Note this currently works only in Viritin's VGrid
            col.getStyle().setColor("gray");
        });

        treeTable.setLevelModel(DirectReportsDto::getLevel);
        // This is kind of handy if read-only nodes, but this is query specific in Oracle so does not work if nodes are skipped in the query
        // TODO figure our an API to TreeTable to disable user expanding/collapsing. Would simplify usage quite a while
        treeTable.setLeafModel(empl -> empl.isLeaf());
        // Use number of direct reports (sub query) to determine if node is leaf, this words even if subtree is ignored in the query
        treeTable.setLeafModel(empl -> empl.getDirectReports() == 0);

        // Our DTO has proper equals and hashCode, so no need
        // for custom implementation, just pass currently closed nodes to backend
        TreeTable.OpenByDefault<DirectReportsDto> openModel = new TreeTable.OpenByDefault<>();
        treeTable.setOpenModel(openModel);

        treeTable.setItems(q -> {
            System.out.println("query for: offset" + q.getOffset() + " l" + q.getLimit() + " closed subtrees: " + openModel.getClosed());
            return service.findAll(q.getOffset(), q.getLimit(), openModel.getClosed()).stream();
        });

        add(treeTable);


    }
}
