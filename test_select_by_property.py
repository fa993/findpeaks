import numpy as np

def _select_by_property(peak_properties, pmin, pmax):
    """
    Evaluate where the generic property of peaks confirms to an interval.

    Parameters
    ----------
    peak_properties : ndarray
        An array with properties for each peak.
    pmin : None or number or ndarray
        Lower interval boundary for `peak_properties`. ``None`` is interpreted as
        an open border.
    pmax : None or number or ndarray
        Upper interval boundary for `peak_properties`. ``None`` is interpreted as
        an open border.

    Returns
    -------
    keep : bool
        A boolean mask evaluating to true where `peak_properties` confirms to the
        interval.

    See Also
    --------
    find_peaks

    Notes
    -----

    .. versionadded:: 1.1.0
    """
    keep = np.ones(peak_properties.size, dtype=bool)
    if pmin is not None:
        keep &= (pmin <= peak_properties)
    if pmax is not None:
        keep &= (peak_properties <= pmax)
    return keep

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

grs = read_lines_in_groups("input.txt")
# assume x is a number
for gr in grs:
    peaks = list(map(float, gr[0].split()))
    pmin = list(map(float, gr[1].split()))
    pmax = list(map(float, gr[2].split()))

    if len(pmin) == 0:
        pmin = None
    elif len(pmin) == 1:
        pmin = pmin[0]
    else:
        pmin = np.array(pmin)

    if len(pmax) == 0:
        pmax = None
    elif len(pmax) == 1:
        pmax = pmax[0]
    else:
        pmax = np.array(pmax)

    out = _select_by_property(np.array(peaks), pmin, pmax)
    print(out.tolist())