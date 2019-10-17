import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BuildChart extends Application {
    static ArrayList<Integer> corners;
    static ArrayList<ArrayList<Integer>> allCorners;

    public BuildChart() {
    }

    public static void setCorners(ArrayList<Integer> cornersIn) {
        corners = cornersIn;
    }

    public static void setAllCorners(ArrayList<ArrayList<Integer>> cornersIn) {
        allCorners = cornersIn;
    }

    private void init(Stage primaryStage) {
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        primaryStage.setScene(new Scene(root));
        NumberAxis xAxis = new NumberAxis("Игры", 0.0D, 20.0D, 1.0D);
        NumberAxis yAxis = new NumberAxis("Число угловых", 0.0D, 20.0D, 1.0D);
        ObservableList<Data<Integer, Integer>> list = FXCollections.observableArrayList();

        for(int i = 0; i < corners.size(); ++i) {
            list.add(new Data(i, corners.get(corners.size() - 1 - i)));
        }

        ObservableList<Series<Integer, Integer>> lineChartData = FXCollections.observableArrayList(new Series[]{new Series("График поданных угловых", list)});
        NumberAxis xAxis2 = new NumberAxis("Игры", 0.0D, 20.0D, 1.0D);
        NumberAxis yAxis2 = new NumberAxis("Разница числа угловых", 0.0D, 10.0D, 1.0D);
        ObservableList<Data<Integer, Integer>> list2 = FXCollections.observableArrayList();

        for(int i = 0; i < corners.size() - 1; ++i) {
            list2.add(new Data(i, Math.abs((Integer)corners.get(corners.size() - 2 - i) - (Integer)corners.get(corners.size() - 1 - i))));
        }

        ObservableList<Series<Integer, Integer>> lineChartData2 = FXCollections.observableArrayList(new Series[]{new Series("График разницы числа угловых", list2)});
        NumberAxis xAxis3 = new NumberAxis("Игры", 0.0D, 20.0D, 1.0D);
        NumberAxis yAxis3 = new NumberAxis("Double разница числа угловых", 0.0D, 10.0D, 1.0D);
        ObservableList<Data<Integer, Integer>> list3 = FXCollections.observableArrayList();

        for(int i = 0; i < corners.size() - 2; ++i) {
            list3.add(new Data(i, Math.abs(Math.abs((Integer)corners.get(corners.size() - 3 - i) - (Integer)corners.get(corners.size() - 2 - i)) - Math.abs((Integer)corners.get(corners.size() - 2 - i) - (Integer)corners.get(corners.size() - 1 - i)))));
        }

        ObservableList<Series<Integer, Integer>> lineChartData3 = FXCollections.observableArrayList(new Series[]{new Series("График double разницы числа угловых", list3)});
        NumberAxis xAxis4 = new NumberAxis("Игры", 0.0D, 20.0D, 1.0D);
        NumberAxis yAxis4 = new NumberAxis("Triple разница числа угловых", 0.0D, 10.0D, 1.0D);
        ObservableList<Data<Integer, Integer>> list4 = FXCollections.observableArrayList();

        for(int i = 0; i < corners.size() - 3; ++i) {
            list4.add(new Data(i, Math.abs(Math.abs(Math.abs((Integer)corners.get(corners.size() - 4 - i) - (Integer)corners.get(corners.size() - 3 - i)) - Math.abs((Integer)corners.get(corners.size() - 3 - i) - (Integer)corners.get(corners.size() - 2 - i))) - Math.abs(Math.abs((Integer)corners.get(corners.size() - 3 - i) - (Integer)corners.get(corners.size() - 2 - i)) - Math.abs((Integer)corners.get(corners.size() - 2 - i) - (Integer)corners.get(corners.size() - 1 - i))))));
        }

        ObservableList<Series<Integer, Integer>> lineChartData4 = FXCollections.observableArrayList(new Series[]{new Series("График triple разницы числа угловых", list4)});
        LineChart chart = new LineChart(xAxis, yAxis, lineChartData);
        LineChart chart2 = new LineChart(xAxis2, yAxis2, lineChartData2);
        LineChart chart3 = new LineChart(xAxis3, yAxis3, lineChartData3);
        LineChart chart4 = new LineChart(xAxis4, yAxis4, lineChartData4);
        root.getChildren().addAll(new Node[]{chart, chart2, chart3, chart4});
    }

    public void start(Stage primaryStage) throws Exception {
        setCorners((ArrayList)allCorners.get(0));
        this.init(primaryStage);
        primaryStage.setMinWidth(2100.0D);
        primaryStage.setTitle("Поданные угловые");
        primaryStage.show();
        setCorners((ArrayList)allCorners.get(1));
        Stage stage = new Stage();
        stage.setMinWidth(2100.0D);
        stage.setTitle("Полученные угловые");
        this.init(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
