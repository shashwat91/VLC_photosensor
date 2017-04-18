# Result Description

## Bit Error-rate

#### Experiment Process

Initially data is acquired from phone's light sensor. [Programm](https://github.com/shashwat91/VLC_photosensor/blob/packet/src/com/example/vlc_photosensor/MainActivity.java) acquire samples from light sensor as soon as possible and store it in the memory. An interrupt goes off every 1ms and it checks for the latest sensor value.
Since sensor gives value in lux, they are converted to 1, 0 or x based on thresholds. These values are represented in files (BER/*_Acquired_bits.svg).
Python [scipt](https://github.com/shashwat91/VLC_photosensor/blob/packet/Frequency%20logs/ber_calculator2.py) reads the threshold values and decode bits based on sample points based on transmitter's frequency. Decoded bits are represented in images (BER/*_sampled_data.svg).
Data files for this experiment is in folder [Frequency logs](https://github.com/shashwat91/VLC_photosensor/tree/packet/Frequency%20logs).

#### Conculsion

Bit error-rate in ZERO uptil 20Hz, and signal is decode-able uptil 50Hz. After 50Hz sample-rate of light sensor cannot keep up with transmitter's frequency and signal becames completely unuseable (BER exceds 40%). This can be seen in [bit_errorRate.svg](https://github.com/shashwat91/VLC_photosensor/blob/packet/Results/bit_errorRate.svg).

## Read raw Values

Number of values given by light sensor in 1 second are recorded. These values follow transmiting frequency uptil 60 Hz, but at 60Hz samples are scattered.
On observation correct bits can be seen uptil 60Hz, it is reprented in [Value_change(samples).svg](https://github.com/shashwat91/VLC_photosensor/blob/packet/Results/Value_change(samples).svg).

## Samples Acquired

Figure [samples_perSecond.svg](https://github.com/shashwat91/VLC_photosensor/blob/packet/Results/samples_perSecond.svg) show the number of samples recorded by phone's light sensor in one second interval for different frequencies. For a system to work number of samples should be atleast equal to bit-rate. Uptil 60Hz bit-rate this criteria is fulfilled. For higher frequencies, message became undecoad-able due to very less samples.