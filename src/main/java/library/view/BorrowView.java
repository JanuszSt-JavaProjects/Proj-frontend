package library.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import library.dto.BookDto;
import library.dto.BorrowDto;
import library.service.BookService;
import library.service.BorrowService;
import library.service.CopyService;

import java.time.LocalDate;
import java.util.List;

@Route("borrow")
public class BorrowView extends VerticalLayout {


    TextField find_byAuthor = new TextField("Find by Author");
    Button button_Find = new Button("Find!");

    IntegerField book_id = new IntegerField("chosen Book Id ");

    IntegerField customer_id = new IntegerField("Enter Customer Id");
    Button button_Confirm = new Button("Confirm Borrow");
    Button button_Back = new Button("Back to Menu");

    Grid<BookDto> books_gird = new Grid<>(BookDto.class);
    BookService bookService;
    CopyService copyService;
    BorrowService borrowService;


    public BorrowView(BookService bookService,
                      BorrowService borrowService,
                      CopyService copyService) {
        this.bookService = bookService;
        this.copyService = copyService;
        this.borrowService = borrowService;

        find_byAuthor.setPlaceholder("e.g.: Dan Brown");


        HorizontalLayout find_Layout = new HorizontalLayout(find_byAuthor, button_Find);
        find_Layout.setAlignItems(Alignment.END);
        books_gird.setColumns("author", "title", "releaseDate");


        button_Find.addClickListener(click -> {
                    books_gird.setItems(findByAuth());
                    find_Layout.add(book_id, customer_id, button_Confirm);
                }
        );


        books_gird.asSingleSelect().addValueChangeListener(event ->
                {
                    button_Confirm.setEnabled(true);
                    if (books_gird.asSingleSelect().isEmpty()) {
                        button_Confirm.setEnabled(false);
                    } else {
                        BookDto book = event.getValue();
                        int id = (int) book.getId();
                        book_id.setValue(id);
                    }
                }
        );

        button_Confirm.addClickListener(click ->

                {
                    if (books_gird.asSingleSelect().getValue() == null) {

                        button_Confirm.setEnabled(false);
                    } else {

                        if(!customer_id.isEmpty()) {
                            saveBorrow();
                            find_Layout.add(button_Back);
                        }
                    }
                }

        );

        button_Back.addClickListener(click ->
                button_Back.getUI().ifPresent(ui ->
                        ui.navigate(""))
        );


        add(find_Layout, books_gird);
    }

    private List<BookDto> findByAuth() {
        return bookService.getByAuthor(
                find_byAuthor.getValue());
    }

    private void saveBorrow() {

        BorrowDto borrowDto = new BorrowDto();

        int copyNr = copyService.getCopyNr(book_id.getValue());
        long clientId = customer_id.getValue();

        borrowDto.setBookId(book_id.getValue());
        borrowDto.setClientId(clientId);
        borrowDto.setCopyId(copyNr);
        borrowDto.setBorrowDate(LocalDate.now());
        borrowDto.setReturnDate(null);

        System.out.println("BorrowView : "+ borrowDto);


        borrowService.save(borrowDto);

        book_id.setVisible(false);
        button_Find.setVisible(false);
        customer_id.setVisible(false);
        find_byAuthor.setVisible(false);
        books_gird.setVisible(false);

        button_Confirm.setText("Done!");
        button_Confirm.setEnabled(false);


    }
}
