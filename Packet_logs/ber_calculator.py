import os
import numpy as np

cwd = os.getcwd()
logFileName = '50_hz_data1.csv'
pathToFile = cwd+'/'+logFileName
logFile  = open(pathToFile, 'r+')
 
read_data = logFile.readlines()
data = [[0 for x in range(2)] for y in range(len(read_data))]
bit = []
for i in range(0,len(read_data)):
	lines = read_data[i].split(':')
	data[i][0] = float(lines[2])
	data[i][1] = float(lines[3])
	if (data[i][1] > 20000):
		bit.append(1)
	elif (data[i][1] < 1000):
		bit.append(0)

print(len(bit))
packet_progress = 0
count = 0
packet = [0 for x in range(8)]
for i in range(0,len(bit)):
	if ((packet_progress == 0) and (bit[i] == 1)):
		if ((bit[i+1] == 0) and (bit[i+2] == 1) and (bit[i+3] == 0)):
			print(data[i][0])
			packet_progress = 1
			i = i+4

	elif ((packet_progress == 1) and (count<8)):
		packet[count] = bit[i]
		count = count+1

	elif ((packet_progress == 1) and (count==8)):
		parity = np.sum(packet)%2
		if parity == bit[i]:
			print(packet)
		else:
			i = i-11
		packet_progress = 0
		count = 0