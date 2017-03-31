#include <TimerOne.h>

#define BUFFER_SIZE 5
#define MAX 50

const int ledpin1 = 11;
int counter1 = 0;
bool ledState = true;
int counter=0;

void setup(void)
{
  pinMode(ledpin1, OUTPUT);
  //* Value for timer = 10^6/freq(hz)
  //Timer1.initialize(1000000);  //1sec interrupt
  Timer1.initialize(1000000);
  Timer1.attachInterrupt(blinkLED);
}
void blinkLED(void)
{
  ledState = !ledState;
  digitalWrite(ledpin1, ledState);
}
void loop(void)
{}
