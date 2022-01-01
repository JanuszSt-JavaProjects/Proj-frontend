package library.frontend.view.admin.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import library.backend.library.domain.Borrow;
import library.backend.library.service.BorrowService;
import library.frontend.view.admin.form.BorrowForm;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


@Route("admin/borrows")
public class AdmBorrowView extends VerticalLayout {

    ApplicationContext context;
    Grid<Borrow> main_Grid = new Grid<>(Borrow.class);
    BorrowService service;

    public AdmBorrowView(ApplicationContext context) {

        this.context = context;
        BorrowForm form = new BorrowForm(this, context);

        service = context.getBean(BorrowService.class);

        Button button_Exit = new Button("Menu");
        button_Exit.setMinWidth(300, Unit.PIXELS);
        button_Exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);


        HorizontalLayout body_Layout = new HorizontalLayout(main_Grid, form);
        body_Layout.setSizeFull();

        Button button_addNewPosition = new Button("Add new Borrow");
        button_addNewPosition.setWidthFull();
        setAlignItems(Alignment.CENTER);

        Button button_modifyPosition = new Button("Modify selected");
        button_modifyPosition.setWidthFull();
        setAlignItems(Alignment.CENTER);
        HorizontalLayout down_Layout = new HorizontalLayout(button_addNewPosition, button_modifyPosition);
        down_Layout.setSizeFull();

        add(body_Layout, down_Layout, button_Exit);

        main_Grid.setColumns("customer.firstname", "customer.lastname", "bookId", "copyId", "borrowDate", "returnDate");


        refresh();
        main_Grid.getColumns().forEach(col -> col.setAutoWidth(true));

        main_Grid.asSingleSelect().addValueChangeListener(event ->
        {
            form.setBorrow(main_Grid.asSingleSelect().getValue());
            form.setSaveDisable();
        });

        button_modifyPosition.addClickListener(click ->
                form.setSaveEnable()
        );


        button_addNewPosition.addClickListener(e -> {

            Borrow borrow = new Borrow();
            borrow.setBorrowDate(LocalDate.MIN);
            form.setBorrow(borrow);
            form.setSaveEnable();
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
