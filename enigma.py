# vim:sw=4:et:ai
#
# Design constaint: ignored notches.

#ROTOT_BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

ROTORS = [
  "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
  "AJDKSIRUXBLHWTMCQGZNPYFVOE",
  "BDFHJLCPRTXVZNYEIWGAKMUSQO"
]

REFLECTOR = "ABCDEFGDIJKGMKMIEBFTCVVJAT"

INITIAL_OFFSETS = ( 10, -8, 10 )
#                   12,  2, 10

def reflect(offset):
    """
    >>> reflect(0)
    24
    >>> reflect(23)
    9
    """
    return (REFLECTOR * 2).index(REFLECTOR[offset], offset + 1) % len(REFLECTOR)

def transform_char(char, rotors, offsets):
    """
    >>> transform_char("E", ["BDFHJLCPRTXVZNYEIWGAKMUSQO"], (-10, 10))
    'M'
    >>> transform_char("V", ["BDFHJLCPRTXVZNYEIWGAKMUSQO"], (-10, 10))
    'R'
    """
    index = ord(char) - ord('A')
    offset = offsets[-1]

    if not rotors:
        return chr(ord('A') + reflect(index + offset))

    rotor = rotors[-1]
    
    enchar = rotor[(index + offset) % 26]
    print 'rotor 1:', enchar
    enchar = transform_char(enchar, rotors[:-1], offsets[:-1])
    print ' aft tfm:', enchar

    # And now, back...
    index = ord(enchar) - ord('A')
    enchar = rotor[(index + offset) % 26]
    enchar = chr(ord('A') + index)
    print 'rotor 2:', index, offset, enchar

    return enchar

def increment_offset(ofs):
    """
    >>> increment_offset((10, 2, 12))
    (10, 1, 13)
    >>> increment_offset((10, 2, 25))
    (10, 1, 0)
    >>> increment_offset((10, 0, 25))
    (10, 25, 0)
    """
    return ofs[:-2] + ((ofs[-2]-1) % 26, (ofs[-1]+1) % 26)

def transform(message, rotors, offsets):
    ofs = offsets
    for c in message:
        ofs = increment_offset(ofs)
        transform_char(c.upper(), rotors, (-sum(ofs),) + ofs)
