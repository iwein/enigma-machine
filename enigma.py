# vim:sw=4:et:ai
#
# Design constaint: ignored notches.

#ROTOT_BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
#ABCDEFGHIJKLMNOPQRSTUVWXYZ"

ROTORS = [
  "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
  "AJDKSIRUXBLHWTMCQGZNPYFVOE",
  "BDFHJLCPRTXVZNYEIWGAKMUSQO"
]

REFLECTOR = "ABCDEFGDIJKGMKMIEBFTCVVJAT"

INITIAL_OFFSETS = ( 'M', 'C', 'K' )

def to_char(c):
    return chr(ord('A') + c)

def reflect(offset):
    """
    >>> reflect(0)
    24
    >>> reflect(23)
    9
    """
    return (REFLECTOR * 2).index(REFLECTOR[offset % 26], (offset % 26) + 1) % len(REFLECTOR)

def to_index(rotor):
    """
    >>> to_index("BDFHJLCPRTXVZNYEIWGAKMUSQO")
    [1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14]
    """
    return map(lambda c: ord(c) - ord('A'), rotor)

def encode_left(offset, rotor):
    """
    >>> encode_left(14, to_index("BDFHJLCPRTXVZNYEIWGAKMUSQO"))
    24
    >>> encode_left(32, to_index("BDFHJLCPRTXVZNYEIWGAKMUSQO"))
    2
    """
    return rotor[offset % 26]

def encode_right(offset, rotor):
    """
    >>> encode_right(4, to_index("BDFHJLCPRTXVZNYEIWGAKMUSQO")) # 4 == 'E'
    15
    >>> encode_right(16, to_index("BDFHJLCPRTXVZNYEIWGAKMUSQO")) # 16 == 'Q'
    24
    >>> encode_right(25, to_index("BDFHJLCPRTXVZNYEIWGAKMUSQO")) # 16 == 'Q'
    12
    """
    #print 'env right', offset
    return rotor.index(offset % 26)


def transform_char(index, rotors, offsets):
    """
    >>> transform_char(4, ["BDFHJLCPRTXVZNYEIWGAKMUSQO"], (-10, 10))
    7
    >>> transform_char(22, ["BDFHJLCPRTXVZNYEIWGAKMUSQO"], (-10, 10))
    23
    """
    offset = offsets[-1]

    index = index + offset

    if not rotors:
        index = reflect(index)
    else:
        rotor = to_index(rotors[-1])
    
        index = encode_left(index, rotor)
        index = transform_char(index, rotors[:-1], offsets[:-1])
        index = encode_right(index, rotor)

    return (index - offset) % 26

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

def relative_offsets(offsets):
    """
    >>> relative_offsets(('M', 'C', 'K'))
    (-12, 10, -8, 10)
    """
    offsets = to_index(offsets)
    offsets.reverse()
    o = offsets[0:1] + [b-a for a, b in zip(offsets, offsets[1:])]
    o.reverse()
    return tuple([-sum(o)] + o)

def transform(message, rotors, offsets):
    """
    >>> transform("ENIGMA REVEALED", ROTORS, INITIAL_OFFSETS)
    QMJIDO MZWZJDMG
    """
    #import pdb
    ofs = relative_offsets(offsets)
    output = []
    #pdb.set_trace()
    for c in message:
        if ord('A') <= ord(c.upper()) <= ord('Z'):
            ofs = increment_offset(ofs)
            output.append(chr(transform_char(ord(c.upper()) - ord('A'), rotors, ofs) + ord('A')))
        else:
            output.append(c)
    print "".join(output)
