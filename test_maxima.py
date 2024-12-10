import warnings

import numpy as np

import numpy as np
from math import ceil

# np.import_array()

__all__ = ['_local_maxima_1d', '_select_by_peak_distance', '_peak_prominences',
           '_peak_widths']


def _local_maxima_1d(x):
    """
    Find local maxima in a 1D array.

    This function finds all local maxima in a 1D array and returns the indices
    for their edges and midpoints (rounded down for even plateau sizes).

    Parameters
    ----------
    x : ndarray
        The array to search for local maxima.

    Returns
    -------
    midpoints : ndarray
        Indices of midpoints of local maxima in `x`.
    left_edges : ndarray
        Indices of edges to the left of local maxima in `x`.
    right_edges : ndarray
        Indices of edges to the right of local maxima in `x`.

    Notes
    -----
    - Compared to `argrelmax` this function is significantly faster and can
      detect maxima that are more than one sample wide. However this comes at
      the cost of being only applicable to 1D arrays.
    - A maxima is defined as one or more samples of equal value that are
      surrounded on both sides by at least one smaller sample.

    .. versionadded:: 1.1.0
    """
#     np.intp_t[::1] midpoints, left_edges, right_edges
#     np.intp_t m, i, i_ahead, i_max

    # Preallocate, there can't be more maxima than half the size of `x`
    midpoints = np.empty(x.shape[0] // 2, dtype=np.intp)
    left_edges = np.empty(x.shape[0] // 2, dtype=np.intp)
    right_edges = np.empty(x.shape[0] // 2, dtype=np.intp)
    m = 0  # Pointer to the end of valid area in allocated arrays

    i = 1  # Pointer to current sample, first one can't be maxima
    i_max = x.shape[0] - 1  # Last sample can't be maxima
    while i < i_max:
        # Test if previous sample is smaller
        if x[i - 1] < x[i]:
            i_ahead = i + 1  # Index to look ahead of current sample

            # Find next sample that is unequal to x[i]
            while i_ahead < i_max and x[i_ahead] == x[i]:
                i_ahead += 1

            # Maxima is found if next unequal sample is smaller than x[i]
            if x[i_ahead] < x[i]:
                left_edges[m] = i
                right_edges[m] = i_ahead - 1
                midpoints[m] = (left_edges[m] + right_edges[m]) // 2
                m += 1
                # Skip samples that can't be maximum
                i = i_ahead
        i += 1

    # Keep only valid part of array memory.
    midpoints.resize(m, refcheck=False)
    left_edges.resize(m, refcheck=False)
    right_edges.resize(m, refcheck=False)

    return midpoints, left_edges, right_edges


def read_double_numbers(filename):
    try:
        with open(filename, 'r') as file:
            # Read and parse the file
            for line in file:
                # Split the line into numbers
                numbers = list(map(float, line.split()))
#                 print(np.array(numbers))
                out = _local_maxima_1d(np.array(numbers))
                print(out[0].tolist())
                print(out[1].tolist())
                print(out[2].tolist())
    except Exception as e:
        print(f"Error reading file: {e}")
        return []

read_double_numbers("input.txt")