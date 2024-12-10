import numpy as np

def _unpack_condition_args(interval, x, peaks):
    """
    Parse condition arguments for `find_peaks`.

    Parameters
    ----------
    interval : number or ndarray or sequence
        Either a number or ndarray or a 2-element sequence of the former. The
        first value is always interpreted as `imin` and the second, if supplied,
        as `imax`.
    x : ndarray
        The signal with `peaks`.
    peaks : ndarray
        An array with indices used to reduce `imin` and / or `imax` if those are
        arrays.

    Returns
    -------
    imin, imax : number or ndarray or None
        Minimal and maximal value in `argument`.

    Raises
    ------
    ValueError :
        If interval border is given as array and its size does not match the size
        of `x`.

    Notes
    -----

    .. versionadded:: 1.1.0
    """
    try:
        imin, imax = interval
    except (TypeError, ValueError):
        imin, imax = (interval, None)

    # Reduce arrays if arrays
    if isinstance(imin, np.ndarray):
        if imin.size != x.size:
            raise ValueError('array size of lower interval border must match x')
        imin = imin[peaks]
    if isinstance(imax, np.ndarray):
        if imax.size != x.size:
            raise ValueError('array size of upper interval border must match x')
        imax = imax[peaks]

    return imin, imax

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
    interval = list(map(float, gr[0].split()))
#     print(interval)
    if len(interval) == 1:
        interval = interval[0]

    x = list(map(float, gr[1].split()))
    peaks = list(map(int, gr[2].split()))

    out = _unpack_condition_args(interval, x, peaks)
    print(out)