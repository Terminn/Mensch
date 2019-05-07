package game;

class PawnImpl implements Pawn {
    private PawnColor color;
    private int[] indexes = new int[4];

    public PawnImpl(PawnColor color) {
        this.color = color;
    }

    @Override
    public int getIndexAtIndex(int index) throws IndexOutOfBoundsException{
        return indexes[index];
    }

    @Override
    public PawnColor getColor() {
        return null;
    }
}
