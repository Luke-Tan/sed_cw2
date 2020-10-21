package ic.doc.camera;

public class Camera {

  private final Sensor sensor;
  private final MemoryCard memoryCard;
  private boolean isPowerOn;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    this.sensor = sensor;
    this.memoryCard = memoryCard;
    this.isPowerOn = false;
  }

  public void pressShutter() {
    if (isPowerOn) {
      memoryCard.write(sensor.readData());
    }
  }

  public void powerOn() {
    this.isPowerOn = true;
    sensor.powerUp();
  }

  public void powerOff() {
    this.isPowerOn = false;
    sensor.powerDown();
  }
}

