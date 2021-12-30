package library.frontend.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import library.backend.library.domain.Book;
import library.backend.library.domain.dto.borrowDto.BorrowDto;
import library.backend.library.mapper.BorrowMapper;
import library.backend.library.service.BookService;
import library.backend.library.service.BorrowService;

import java.util.List;

@Route("borrow")
public class BorrowView extends VerticalLayout {


    TextField find_byAuthor = new TextField("Find by Author");
    Button button_Find = new Button("Find!");

    IntegerField input_Id = new IntegerField("Enter Customer Id");
    TextField input_Lastname = new TextField("Enter Customer Lastname");
    Button button_Confirm = new Button("Confirm Borrow");
    Button button_Back = new Button("Back to Menu");

    Grid<Book> books_gird = new Grid<>(Book.class);
    BookService bookService;

    public BorrowView(BookService bookService,
                      BorrowService borrowService,
                      BorrowMapper borrowMapper) {
        this.bookService = bookService;

        find_byAuthor.setPlaceholder("e.g.: Dan Brown");


        HorizontalLayout find_Layout = new HorizontalLayout(find_byAuthor, button_Find);
        find_Layout.setAlignItems(Alignment.END);
        books_gird.setColumns("author", "title", "releaseDate");


        button_Find.addClickListener(click -> {
                    books_gird.setItems(findBooks());
                    find_Layout.add(input_Id, input_Lastname, button_Confirm);
                }
        );

        BorrowDto borrowDto = new BorrowDto();

        books_gird.asSingleSelect().addValueChangeListener(event ->
                {
                    Book book = event.getValue();
                    borrowDto.setBookId(book.getId());
                    borrowDto.setCopyId(book.getCopies().get(0).getId());
                }

        );

        button_Confirm.addClickListener(click ->

                {
                    borrowDto.setClientId(input_Id.getValue());
                    borrowService.save(borrowMapper.mapBorrowDtoToBorrow(borrowDto));

                    button_Find.setVisible(false);
                    input_Id.setVisible(false);
                    input_Lastname.setVisible(false);
                    find_byAuthor.setVisible(false);
                    books_gird.setVisible(false);

                    button_Confirm.setText("Done!");
                    button_Confirm.setEnabled(false);
                    find_Layout.add(button_Back);


                }

        );

        button_Back.addClickListener(click ->
                button_Back.getUI().ifPresent(ui ->
                        ui.navigate(""))
        );


        add(find_Layout, books_gird);
    }


    private Book setBookToBorrow(Book book) {
        return book;

    }

    private List<Book> findBooks() {
        return bookService.getByAuthor(
                find_byAuthor.getValue());
    }

}
