package library.frontend.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("library")
public class LibraryView extends VerticalLayout {


    Button borrow = new Button("Borrow a Book");
    Button admin = new Button("Administration");

    LibraryView(){

        setAlignItems(Alignment.CENTER);

        borrow.setMinHeight(100, Unit.PIXELS);
        borrow.setMinWidth(300, Unit.PIXELS);
        admin.setMinHeight(100, Unit.PIXELS);
        admin.setMinWidth(300, Unit.PIXELS);




        borrow.addClickListener(e ->
                borrow.getUI().ifPresent(ui ->
                        ui.navigate("borrow")));

        admin.addClickListener(e ->
                admin.getUI().ifPresent(ui ->
                        ui.navigate("admin"))
        );

        add(borrow,admin);

    }



}
