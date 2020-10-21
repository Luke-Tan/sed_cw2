package ic.doc.camera;

public class Camera implements WriteListener {

  private final Sensor sensor;
  private final MemoryCard memoryCard;
  private boolean isPowerOn;
  private boolean isWriting;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    this.sensor = sensor;
    this.memoryCard = memoryCard;
    this.isPowerOn = false;
    this.isWriting = false;
  }

  public void pressShutter() {
    if (isPowerOn) {
      isWriting = true;
      memoryCard.write(sensor.readData());
    }
  }

  public void powerOn() {
    isPowerOn = true;
    sensor.powerUp();
  }

  public void powerOff() {
    isPowerOn = false;
    if (!isWriting) {
      sensor.powerDown();
    }
  }

  @Override
  public void writeComplete() {
    isWriting = false;
  }
}

