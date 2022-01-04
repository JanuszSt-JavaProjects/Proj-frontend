package library.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import library.weather.WeatherClient;
import library.weather.dto.WeatherDto;

import javax.swing.*;

@Route("")
public class StartView extends VerticalLayout {


    Button libraryBtn = new Button("Enter Library");
    Button readersBtn = new Button("Enter Readers Room");

    VerticalLayout signLayout = new VerticalLayout();
    Text signTxt = new Text(Sign.getInstance().toString());

    StartView() {

        signLayout.add(signTxt);

        libraryBtn.setMinHeight(100, Unit.PIXELS);
        libraryBtn.setMinWidth(300, Unit.PIXELS);
        readersBtn.setMinHeight(100, Unit.PIXELS);
        readersBtn.setMinWidth(300, Unit.PIXELS);


        WeatherDto weatherDto = new WeatherClient().getWeather();

        Text temperature = new Text("Now in Warsaw :   temperature is: " + String.valueOf(weatherDto.getTemp())+"  st.C  ");
        Text weather = new Text("and the sky looks like  " + String.valueOf(weatherDto.getWeather()));


        libraryBtn.addClickListener(e ->
                libraryBtn.getUI().ifPresent(ui ->
                        ui.navigate("library")
                )
        );

        readersBtn.addClickListener(e ->
                readersBtn.getUI().ifPresent(ui ->
                        ui.navigate("readers")
                )
        );

       VerticalLayout lay =  new VerticalLayout(temperature, new HorizontalLayout(),weather);
       lay.setAlignItems(Alignment.CENTER);

        add(libraryBtn, readersBtn,lay,signTxt);
        setAlignItems(Alignment.CENTER);
    }

}
