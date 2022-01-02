/*
package library.view;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import library.backend.readingRoom.NationalLibrary.domain.NatLibBookDto;
import library.backend.readingRoom.NationalLibrary.service.NatLibService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Route("readers")
public class ReaderView extends VerticalLayout {

    NatLibService natLibService;
    ApplicationContext applicationContext;

    public ReaderView(NatLibService natLibService,
                      ApplicationContext applicationContext) {
        this.natLibService = natLibService;
        this.applicationContext = applicationContext;


        TextField enter_author = new TextField("Enter Author");
        TextField enter_title = new TextField("Enter title");
        Button checkAccessibilityButton = new Button("Check if Accessible!");
        TextArea textArea = new TextArea();
        Button schedule_Button = new Button("Set Your Schedule!");

        Button button_Exit = new Button("<-- Menu");
        button_Exit.setMinWidth(300, Unit.PIXELS);
        button_Exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        Grid<NatLibBookDto> natLibBookDtoGrid = new Grid<>(NatLibBookDto.class);
        natLibBookDtoGrid.setColumns("id", "author", "title");
        natLibBookDtoGrid.setVisible(true);


        textArea.setMinWidth(800, Unit.PIXELS);

        checkAccessibilityButton.setMinWidth(400, Unit.PIXELS);


        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(enter_author, enter_title);

        VerticalLayout layout1Left = new VerticalLayout(topLayout, checkAccessibilityButton);
        VerticalLayout layout2Right = new VerticalLayout(textArea, schedule_Button);


        // books
        Grid<NatLibBookDto> books = new Grid<>(NatLibBookDto.class);
        books.setColumns("author", "title");

        checkAccessibilityButton.addClickListener(event -> {
            List<NatLibBookDto> arrayList = new ArrayList<>(natLibService.getBooks(
                    enter_author.getValue(), enter_title.getValue()
            ));
            books.setItems(arrayList);
        });


        books.asSingleSelect().addValueChangeListener(event -> {


            NatLibBookDto selectedBook = event.getValue();
            textArea.setValue(showBook(selectedBook).toString());
        });


        HorizontalLayout top_Bar = new HorizontalLayout(layout1Left, layout2Right);
        top_Bar.setDefaultVerticalComponentAlignment(Alignment.END);


        schedule_Button.addClickListener(e ->
                schedule_Button.getUI().ifPresent(ui ->
                        ui.navigate("reservation")));

        button_Exit.addClickListener(e ->
                button_Exit.getUI().ifPresent(ui ->
                        ui.navigate("")
                )
        );

        VerticalLayout foot_Layout = new VerticalLayout(button_Exit);
        foot_Layout.setSizeFull();
        foot_Layout.setAlignItems(Alignment.CENTER);

        add(top_Bar,
                books,
                foot_Layout);
    }


    private NatLibBookDto showBook(NatLibBookDto natLibBookDto) {
        String title = natLibBookDto.getTitle();
        String newTitle = title.substring(0, title.indexOf("/"));
        natLibBookDto.setTitle(newTitle);

        NatLibBookDto tempNatLibDto = applicationContext.getBean("selectedBook", NatLibBookDto.class);
        tempNatLibDto.setId(natLibBookDto.getId());
        tempNatLibDto.setAuthor(natLibBookDto.getAuthor());
        tempNatLibDto.setTitle(natLibBookDto.getTitle());

        return natLibBookDto;
    }
}

*/
