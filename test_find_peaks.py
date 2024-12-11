import numpy as np
from scipy.signal import find_peaks

def read_lines_in_groups(filename, group_size=3):
    try:
        with open(filename, 'r') as file:
            group = []
            for line in file:
                group.append(line.strip())  # Remove any extra whitespace/newlines
                if len(group) == group_size:
                    yield group
                    group = []  # Reset the group for the next set of lines

#             # Yield any remaining lines if they're fewer than the group size
#             if group:
#                 yield group
    except Exception as e:
        print(f"Error reading file: {e}")


def parse_into_type(interval, x):
    if len(interval) > len(x):
        interval = np.array(interval)
        interval = interval.reshape((2, -1))
    elif len(interval) == 1:
        interval = interval[0]
    elif len(interval) == 0:
        interval = None
    else:
        interval = np.array(interval)
    return interval

grs = read_lines_in_groups("input.txt", group_size=9)
# assume x is a number
for gr in grs:
    x = list(map(float, gr[0].split()))
    height = parse_into_type(list(map(float, gr[1].split())), x)
    threshold = parse_into_type(list(map(float, gr[2].split())), x)
    distance = parse_into_type(list(map(float, gr[3].split())), x)
    prominence = parse_into_type(list(map(float, gr[4].split())), x)
    width = parse_into_type(list(map(float, gr[5].split())), x)
    wlen = parse_into_type(list(map(int, gr[6].split())), x)
    rel_height = parse_into_type(list(map(float, gr[7].split())), x)
    if rel_height is None:
        rel_height = 0.5 # default
    plateau_size = parse_into_type(list(map(float, gr[8].split())), x)

    peaks, properties = find_peaks(x, height=height, threshold=threshold, distance=distance, prominence=prominence, width=width, wlen=wlen, rel_height=rel_height, plateau_size=plateau_size)
#     Custom print format
    print(1 + len(properties))
    print(" ".join(map(str, peaks)))
    for key, value in properties.items():
        print(key, " ".join(map(str, value)))
