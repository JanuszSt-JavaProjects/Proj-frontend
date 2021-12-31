package library.frontend.view.admin;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import library.backend.library.domain.Customer;
import library.backend.library.service.CustomerService;
import library.frontend.view.admin.form.CustomerForm;
import org.springframework.context.ApplicationContext;


@Route("admin/customers")

public class AdmCustomView extends VerticalLayout {

    ApplicationContext context;
    Grid<Customer> main_Grid = new Grid<>(Customer.class);
    CustomerService service;


    public AdmCustomView(ApplicationContext context) {
        this.context = context;
        CustomerForm form = new CustomerForm(this, context);

        service = context.getBean(CustomerService.class);

        Button button_Exit = new Button("Menu");
        button_Exit.setMinWidth(300, Unit.PIXELS);
        button_Exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);


        Button button_addNewPosition = new Button("Add new Customer");
        button_addNewPosition.setWidthFull();
        setAlignItems(Alignment.CENTER);

        HorizontalLayout body_Layout = new HorizontalLayout(main_Grid, form);
        body_Layout.setSizeFull();

        add(body_Layout, button_addNewPosition, button_Exit);

        main_Grid.setColumns("firstname", "lastname", "createAccountDate");

        refresh();

        main_Grid.asSingleSelect().addValueChangeListener(event -> form.setCustomer(main_Grid.asSingleSelect().getValue()));


        button_addNewPosition.addClickListener(e -> {
            main_Grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });

        button_Exit.addClickListener(e ->
                button_Exit.getUI().ifPresent(ui ->
                        ui.navigate("admin")
                )
        );
    }

    public void refresh() {
        main_Grid.setItems(service.getAll());
    }
}
