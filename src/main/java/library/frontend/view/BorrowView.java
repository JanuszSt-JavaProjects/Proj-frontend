package library.frontend.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import library.backend.library.domain.Book;
import library.backend.library.service.BookService;

import java.util.List;

@Route("borrow")
public class BorrowView extends VerticalLayout {


    TextField find_byAuthor = new TextField("Find by Author");
//    TextField find_byTitle = new TextField("Find by Title");
    Button find_Button = new Button("Find!");

    TextField input_Id = new TextField("input Your Id");
    TextField input_Lastname = new TextField("input Your Lastname");

    Grid<Book> books_gird = new Grid<>(Book.class);
    BookService bookService;

    public BorrowView(BookService bookService) {
        this.bookService = bookService;

        HorizontalLayout find_Layout = new HorizontalLayout(find_byAuthor/*,find_byTitle*/, find_Button);
        find_Layout.setAlignItems(Alignment.END);
        books_gird.setColumns("author", "title", "releaseDate");


        find_Button.addClickListener(click -> {
                    books_gird.setItems(findBooks());
                }
        );


        add(find_Layout, books_gird);
    }

    private List<Book> findBooks() {
        return bookService.getByAuthor(
                find_byAuthor.getValue());
    }

}
