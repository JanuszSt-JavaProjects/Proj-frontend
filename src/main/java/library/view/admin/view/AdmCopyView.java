package library.view.admin.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import library.dto.ConvertedCopyDto;
import library.dto.CopyDto;
import library.service.CopyConverter;
import library.service.CopyService;
import library.view.admin.form.CopyForm;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Route("admin/copies")
public class AdmCopyView extends VerticalLayout {

    ApplicationContext context;
    Grid<ConvertedCopyDto> main_Grid = new Grid<>(ConvertedCopyDto.class);
    CopyService service;
    CopyConverter copyConverter;

    public AdmCopyView(ApplicationContext context) {

        this.context = context;
        CopyForm form = new CopyForm(this, context);

        service = context.getBean(CopyService.class);
        copyConverter = context.getBean(CopyConverter.class);

        Button button_Exit = new Button("Menu");
        button_Exit.setMinWidth(300, Unit.PIXELS);
        button_Exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);


        Button button_addNewPosition = new Button("Add new Copy");
        button_addNewPosition.setWidthFull();
        setAlignItems(Alignment.CENTER);

        HorizontalLayout body_Layout = new HorizontalLayout(main_Grid, form);
        body_Layout.setSizeFull();


        setAlignItems(Alignment.CENTER);
        HorizontalLayout down_Layout = new HorizontalLayout(button_addNewPosition);
        down_Layout.setSizeFull();

        add(body_Layout, down_Layout, button_Exit);
        main_Grid.setColumns("bookId","id", "signature", "statusDto");

        refresh();
        main_Grid.getColumns().forEach(col -> col.setAutoWidth(true));

        main_Grid.asSingleSelect().addValueChangeListener(event ->
        {
            form.setCopy(main_Grid.asSingleSelect().getValue());
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
        List<CopyDto> input = new ArrayList<>(service.getAll());

        List<ConvertedCopyDto> output = new ArrayList<>();

        input.forEach(x -> output.add(copyConverter.convertToConvertedCopyDto(x)));


        main_Grid.setItems(output);


    }
}
