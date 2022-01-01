package library.frontend.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.backend.library.domain.Borrow;
import library.backend.library.domain.Customer;
import library.backend.library.service.BorrowService;
import library.backend.library.service.CustomerService;
import library.frontend.view.admin.view.AdmBorrowView;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

public class BorrowForm extends FormLayout {

    AdmBorrowView admBorrowView;
    ApplicationContext context;
    BorrowService borrowService;
    CustomerService customerService;

    private IntegerField int_BookId = new IntegerField("book Id");
    private IntegerField int_CopyId = new IntegerField("copy Id");
    private IntegerField int_CustomerId = new IntegerField("customer Id");


    private TextField any_BorrowDate = new TextField("borrow date");
    private TextField any_ReturnDate = new TextField("return date");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");

    HorizontalLayout buttons = new HorizontalLayout(modify, save, delete);


    private final Binder<Borrow> binder = new Binder<>(Borrow.class);


    public BorrowForm(AdmBorrowView admBorrowView,
                      ApplicationContext context) {

        this.admBorrowView = admBorrowView;
        this.context = context;
        borrowService = context.getBean(BorrowService.class);
        customerService = context.getBean(CustomerService.class);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setVisible(false);
        modify.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modify.setVisible(false);


        save.addClickListener(click -> save());
        modify.addClickListener(click -> update());
        delete.addClickListener(click -> delete());


        VerticalLayout layout_Right =
                new VerticalLayout(new HorizontalLayout(int_BookId,
                        int_CopyId),
                        int_CustomerId,
                        any_BorrowDate,
                        any_ReturnDate,
                        buttons);

        add(layout_Right);
    }


    public void setSaveAction() {
        setEnabled(true);
        setBorrow(new Borrow());

        modify.setVisible(false);
        save.setVisible(true);
        save.setEnabled(true);
        System.out.println("       -> wychodzę z setSave Action");

    }

    public void setUpdateAction() {
        setEnabled(true);
        save.setVisible(false);
        modify.setVisible(true);
    }


    public void setBorrow(Borrow borrow) {
        System.out.println("     set borrow :    ======================>>>>>>>>>    " + borrow);

        binder.setBean(borrow);

        if (borrow == null) {
            setVisible(false);
        } else if (borrow.getCustomer()==null) {
            int_BookId.setValue(0);
            int_CopyId.setValue(0);
            int_CustomerId.setValue(0);
            any_BorrowDate.setValue(LocalDate.now().toString());
        } else {

            int_CustomerId.setValue((int) borrow.getCustomer().getId());
            int_BookId.setValue((int) borrow.getBookId());
            int_CopyId.setValue((int) borrow.getCopyId());
            any_BorrowDate.setValue(borrow.getBorrowDate().toString());
            any_ReturnDate.setValue(
                    borrow.getReturnDate() == null ? "" : borrow.getReturnDate().toString()
            );
            setVisible(true);


            try {
                binder.bindInstanceFields(this);
            } catch (RuntimeException e) {

                /*
                 *  ADD LOGGER -> database
                 * */
            }
        }
    }


    private void save() {

        Borrow borrow = binder.getBean();

        extracted(borrow, any_BorrowDate);

        borrowService.save(borrow);
        admBorrowView.refresh();
        setBorrow(borrow);
        setDisable();
    }

    private void update() {
        Borrow borrow = binder.getBean();

        extracted(borrow, any_ReturnDate);

        borrowService.update(borrow);
        admBorrowView.refresh();
        setBorrow(borrow);
        setDisable();
    }

    private void extracted(Borrow borrow, TextField any_returnDate) {
        borrow.setBorrowDate(LocalDate.parse(any_BorrowDate.getValue()));
        borrow.setReturnDate(LocalDate.parse(any_returnDate.getValue()));

        long customer_id = int_CustomerId.getValue();
        Customer customer = customerService.getOne(customer_id);
        borrow.setCustomer(customer);

        borrow.setBookId(int_BookId.getValue());
        borrow.setCopyId(int_CopyId.getValue());
    }

    private void delete() {

        Borrow borrow = binder.getBean();
        long id = borrow.getId();
        borrowService.remove(id);
        admBorrowView.refresh();
        setBorrow(borrow);
        setDisable();
    }

    void setDisable() {
        setEnabled(false);
    }

}


