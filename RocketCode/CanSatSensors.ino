// Pin assignments for the MQ sensors
#define MQ135_PIN 34  // GPIO 34 for MQ-135 (Air Quality sensor)
#define MQ3_PIN 35    // GPIO 35 for MQ-3 (Alcohol sensor)
#define MQ9_PIN 32    // GPIO 32 for MQ-9 (CO & Combustible gases sensor)

void setup() {
  // Initialize Serial Monitor
  Serial.begin(115200);

  // ADC setup
  analogReadResolution(12);       // Set ADC resolution to 12 bits (0-4095)
  analogSetAttenuation(ADC_11db); // Use full voltage range (0 to 3.3V)
}

void loop() {
  // Read raw analog values
  int mq135_raw = analogRead(MQ135_PIN);
  int mq3_raw = analogRead(MQ3_PIN);
  int mq9_raw = analogRead(MQ9_PIN);

  // Convert raw values to percentage
  float mq135_percentage = (mq135_raw / 4095.0) * 100;
  float mq3_percentage = (mq3_raw / 4095.0) * 100;
  float mq9_percentage = (mq9_raw / 4095.0) * 100;

  // Print readings to the Serial Monitor
  Serial.println("----- Gas Sensor Readings -----");
  Serial.printf("MQ-135 (Air Quality): Raw=%d, Percentage=%.2f%%\n", mq135_raw, mq135_percentage);
  Serial.printf("MQ-3 (Alcohol): Raw=%d, Percentage=%.2f%%\n", mq3_raw, mq3_percentage);
  Serial.printf("MQ-9 (CO & Gas): Raw=%d, Percentage=%.2f%%\n", mq9_raw, mq9_percentage);
  Serial.println("--------------------------------");

  // Wait before next reading
  delay(2000);
}
