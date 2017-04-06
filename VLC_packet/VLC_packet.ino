#include <TimerOne.h>

const int ledpin1 = 11;
const int ledpin2 = 10;
const int nMsg = 3;
const int packetLen = 13;
const int headerLen = 4;
const int msgLength = 8;

bool ledState = false;
int parity;
int pc=0,bc=0;

int msg1[nMsg][msgLength] = {
  {0,1,0,1,0,1,1,0}, //0x56 -- V
  {0,1,0,0,1,1,0,0}, //0x4C -- L
  {0,1,1,0,0,0,1,1}, //0x63 -- c
};
int packet[nMsg][packetLen];
int header[headerLen] = {1,0,1,0};

void setup(void)
{
  makePacket();
  //printPackets();
  noInterrupts();
  pinMode(ledpin1, OUTPUT);
  pinMode(ledpin2, OUTPUT);
  // Value for timer = 10^6/freq(hz)
  Timer1.initialize(20000);
  Timer1.attachInterrupt(blinkLED);
  interrupts();
}
void blinkLED(void)
{
  if(packet[pc][bc] == 1)
  {     
      digitalWrite(ledpin1, HIGH);
      //digitalWrite(ledpin2, LOW);
  }
      
  if(packet[pc][bc] == LOW )
  {
       digitalWrite(ledpin1, LOW); 
       //digitalWrite(ledpin2, HIGH);
  }
      
  if(++bc == packetLen)
  {
    pc++;
    bc = 0;
    if(pc == nMsg)
      pc = 0;
  }
}
void makePacket()
{
  int k,j;
  for(int i=0; i<nMsg; ++i)
  {
    k=0;j=0;
    parity =0;
    while(k<headerLen)
    {
      packet[i][k++] = header[j++];
    }
    j=0;
    while(k<headerLen+msgLength)
    {
      packet[i][k++] = msg1[i][j];
      parity += msg1[i][j++];
    }
    packet[i][k] = parity%2;
  }
}
void printPackets()
{
  for(int i=0; i<nMsg ;++i)
  {
    Serial.print("Packet number ");
    Serial.print(i);
    Serial.print(":");
    for(int j=0; j<packetLen; ++j)
      Serial.print(packet[i][j]);
    Serial.println("");
  }
}
void loop(void)
{}
