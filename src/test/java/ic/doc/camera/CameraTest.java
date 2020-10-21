package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {
  private Camera camera;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  private Sensor sensor = context.mock(Sensor.class);
  private MemoryCard memoryCard = context.mock(MemoryCard.class);

  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {
    camera = new Camera(sensor, memoryCard);

    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerUp();
    }});

    camera.powerOn();
  }

  @Test
  public void switchingTheCameraOffPowersDownTheSensor() {
    camera = new Camera(sensor, memoryCard);

    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerDown();
    }});

    camera.powerOff();
  }

  @Test
  public void pressingTheShutterWithThePowerOnCopiesDataFromTheSensorToTheMemoryCard() {
    camera = new Camera(sensor, memoryCard);
    byte[] mockedSensorReadData = {1, 2, 3, 4};

    context.checking(new Expectations() {{
      ignoring(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(mockedSensorReadData));
      exactly(1).of(memoryCard).write(mockedSensorReadData);
    }});

    camera.powerOn();
    camera.pressShutter();
  }

}
