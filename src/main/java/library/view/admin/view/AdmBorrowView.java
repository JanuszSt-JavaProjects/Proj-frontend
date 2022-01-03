package library.view.admin.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import library.dto.BorrowDto;
import library.dto.ConvertedBorrowDto;
import library.dto.ConvertedCopyDto;
import library.dto.CopyDto;
import library.service.BorrowService;
import library.service.converter.BorrowConverter;
import library.view.admin.form.BorrowForm;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;


@Route("admin/borrows")
public class AdmBorrowView extends VerticalLayout {

    ApplicationContext context;
    Grid<ConvertedBorrowDto> main_Grid = new Grid<>(ConvertedBorrowDto.class);
    BorrowService service;
    BorrowConverter borrowConverter;

    public AdmBorrowView(ApplicationContext context) {

        this.context = context;

        BorrowForm form = new BorrowForm(this, context);

        service = context.getBean(BorrowService.class);
        borrowConverter = context.getBean(BorrowConverter.class);

        Button button_Exit = new Button("Menu");
        button_Exit.setMinWidth(300, Unit.PIXELS);
        button_Exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        Button button_addNewPosition = new Button("Add new Borrow");
        button_addNewPosition.setWidthFull();
        setAlignItems(Alignment.CENTER);

        HorizontalLayout body_Layout = new HorizontalLayout(main_Grid, form);
        body_Layout.setSizeFull();


        setAlignItems(Alignment.CENTER);
        HorizontalLayout down_Layout = new HorizontalLayout(button_addNewPosition);
        down_Layout.setSizeFull();

        add(body_Layout, down_Layout, button_Exit);

        main_Grid.setColumns(
                "bookId",
                "copyId",
                "clientId",
                "borrowDate",
                "returnDate");

        refresh();
        main_Grid.getColumns().forEach(col -> col.setAutoWidth(true));

        main_Grid.asSingleSelect().addValueChangeListener(event ->
        {

            ConvertedBorrowDto borrow = main_Grid.asSingleSelect().getValue();
            form.setEnabled(false);
            form.setBorrow(borrow);
            form.setUpdateAction();

        });


        button_addNewPosition.addClickListener(e -> {
            main_Grid.asSingleSelect().clear();
            form.setSaveAction();

        });


        button_Exit.addClickListener(e ->
                button_Exit.getUI().ifPresent(ui ->
                        ui.navigate("admin")
                )
        );
    }

    public void refresh() {

        List<BorrowDto> input = new ArrayList<>(service.getAll());

        List<ConvertedBorrowDto> output = new ArrayList<>();
        input.forEach(x -> output.add(borrowConverter.convertToConvertedBDto(x)));

        main_Grid.setItems(output);
    }
}
