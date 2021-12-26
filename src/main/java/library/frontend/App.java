package library.frontend;//package finalProject.frontend;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.HtmlObject;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import library.backend.library.domain.Book;
import library.backend.library.service.BookService;
import library.backend.library.service.CopyService;


import java.util.ArrayList;
import java.util.List;

@Route("")
class App extends VerticalLayout {

    public App(BookService bookService, CopyService copyService) {

        Button button = new Button("click me!");
        button.addClickListener(event -> {

        });

        button.addClickListener(event -> {
            getUI().get().getPage().open("forecast.html", "_self");
        });


        HorizontalLayout layout = new HorizontalLayout(button, new DatePicker("Pick a date"));

        layout.setDefaultVerticalComponentAlignment(Alignment.END);

        HtmlObject htmlComponent = new HtmlObject("forecast.html");

        htmlComponent.setSizeFull();
        htmlComponent.setHeight("400px");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(htmlComponent);

        Grid<Book> books = new Grid<>(Book.class);
        List<Book> arrayList = new ArrayList<>(bookService.getAll());
        books.setColumns("author", "title", "copies");
        books.setItems(arrayList);


        books.getColumns().forEach(col->col.setAutoWidth(true));

        add(verticalLayout,
                layout,
                books);
    }


}
