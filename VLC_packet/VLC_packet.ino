#include <TimerOne.h>

const int ledpin1 = 11;
bool ledState = false;
int counter = 0;
int packet[8] = {0,1,1,1,1,0,1,1}; //0x7b

void setup(void)
{
  noInterrupts();   
  pinMode(ledpin1, OUTPUT);
  Timer1.initialize(2000);
  Timer1.attachInterrupt(blinkLED);
  interrupts();
}
void blinkLED(void)
{
  if(packet[counter] == 1)
  {     
      digitalWrite(ledpin1, HIGH);  
  }
      
  if(packet[counter] == LOW )
  {
    //if(digitalRead(ledpin1) == HIGH)
       digitalWrite(ledpin1, LOW); 
  }
      
  counter++;
  if(counter == 8)
    counter = 0;  
}
void loop(void)
{}
