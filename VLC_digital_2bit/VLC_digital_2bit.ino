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
  Timer1.initialize(4000);
  Timer1.attachInterrupt(blinkLED);
}
void blinkLED(void)
{
  
  if(counter == 0)
    ledState = !ledState;
  else if(counter == 2)
    ledState = !ledState;
  digitalWrite(ledpin1, ledState);
  counter++;
  if(counter == 3)
    counter=0;
}
void loop(void)
{}
