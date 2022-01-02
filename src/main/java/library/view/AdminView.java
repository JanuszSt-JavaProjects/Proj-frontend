package library.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route("admin")
public class AdminView extends VerticalLayout {

    Button button_Books = new Button("books");
    Button button_Copies = new Button("copies");
    Button button_Borrows = new Button("borrows");
    Button button_Customers = new Button("customers");



    AdminView() {
        setAlignItems(Alignment.CENTER);

        button_Customers.setMinHeight(80, Unit.PIXELS);
        button_Customers.setMinWidth(300, Unit.PIXELS);
        button_Customers.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        button_Books.setMinHeight(80, Unit.PIXELS);
        button_Books.setMinWidth(300, Unit.PIXELS);
        button_Books.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);


        button_Copies.setMinHeight(80, Unit.PIXELS);
        button_Copies.setMinWidth(300, Unit.PIXELS);
        button_Copies.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        button_Borrows.setMinHeight(80, Unit.PIXELS);
        button_Borrows.setMinWidth(300, Unit.PIXELS);
        button_Borrows.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);


        button_Books.addClickListener(e ->
                button_Books.getUI().ifPresent(ui ->
                        ui.navigate("admin/books")));

        button_Copies.addClickListener(e ->
                button_Copies.getUI().ifPresent(ui ->
                        ui.navigate("admin/copies"))
        );

        button_Borrows.addClickListener(e ->
                button_Borrows.getUI().ifPresent(ui ->
                        ui.navigate("admin/borrows"))
        );

        button_Customers.addClickListener(e ->
                button_Customers.getUI().ifPresent(ui ->
                        ui.navigate("admin/customers"))
        );



        add(button_Customers,button_Books, button_Copies,button_Borrows,new RouterLink("back", StartView.class));
    }

}

