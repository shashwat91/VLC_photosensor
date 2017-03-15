#include <TimerOne.h>

#define BUFFER_SIZE 5
#define MAX 50

const int ledpin1 = 11;
int counter1 = 0;
bool ledState = false;

void setup(void)
{
  pinMode(ledpin1, OUTPUT);
  Timer1.initialize(5000);
  Timer1.attachInterrupt(blinkLED);
}
void blinkLED(void)
{
  digitalWrite(ledpin1, ledState);
  ledState = !ledState;
}
void loop(void)
{}
