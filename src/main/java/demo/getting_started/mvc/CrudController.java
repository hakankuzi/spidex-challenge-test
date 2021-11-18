package demo.getting_started.mvc;

import demo.getting_started.repository.MockDataService;
import demo.getting_started.repository.MockDataServiceImpl;
import demo.getting_started.tutorial.Car;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

public class CrudController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;

    @Wire
    private Textbox txtModel;

    @Wire
    private Textbox txtColour;

    @Wire
    private Textbox txtMake;

    @Wire
    private Textbox txtPrice;

    @Wire
    private Textbox txtDescription;

    @Wire
    private Textbox txtPreview;

    @Wire
    private Button saveBtn;

    MockDataService mockDataService = new MockDataServiceImpl();

    @Listen("onClick = #saveBtn")
    public void saveButton() {
        Car car = new Car();
        car.setColour(txtColour.getValue());
        car.setModel(txtModel.getValue());
        car.setMake(txtMake.getValue());
        car.setPrice(Integer.valueOf(txtPrice.getValue()));
        car.setDescription(txtDescription.getValue());
        car.setPreview(txtPreview.getValue());
        mockDataService.addCar(car);
        Executions.sendRedirect("/");
    }


}