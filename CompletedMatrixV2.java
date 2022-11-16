package edu.ser222.m01_02;

/**
 * An implementation of the Matrix ADT. Provides four basic operations over an immutable type.
 * 
 * Last updated 7/31/2021.
 * 
 * @author David McConnell, Ruben Acuna
 * @version 1.3
 */


public class CompletedMatrixV2 implements Matrix {
    private final int[][] matrix;

    public CompletedMatrixV2(int[][] matrix) throws IllegalArgumentException {
        if(matrix == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        if(matrix.length==0||matrix[0].length==0) {
            this.matrix = new int[0][0];
        }
        else {
            int[][] tmp = new int[matrix.length][matrix[0].length];
            for(int i=0; i < matrix.length; i++) {
                for(int j=0; j < matrix[0].length; j++) {
                    tmp[i][j] = matrix[i][j];
                }
            }
            this.matrix = tmp;
        }
    }

    @Override
    public int getElement(int y, int x) {
        return matrix[y][x];
    }

    @Override
    public int getRows() {
        if(matrix.length==0) {
            return 0;
        } else {
            return matrix.length;           
        }
    }

    @Override
    public int getColumns() {
        if(matrix.length==0) {
            return 0;
        } else {
            return matrix[0].length;
        }
    }

    @Override
    public Matrix scale(int scalar) {
        int[][] tmp = new int[this.getRows()][this.getColumns()];
        for(int i=0; i < this.getRows(); i++) {
            for(int j=0; j<this.getColumns(); j++) {
                tmp[i][j] = this.getElement(i, j)*scalar;
            }
        }
        return new CompletedMatrixV2(tmp);
    }

    @Override
    public Matrix plus(Matrix other) {
        if(other==null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        if(this.getRows()!=other.getRows() || this.getColumns()!=other.getColumns()) {
            throw new RuntimeException("Matrices must have matching dimension.");
        }
        int[][] tmp = new int[this.getRows()][this.getColumns()];
        for(int i=0; i < this.getRows(); i++) {
            for(int j=0; j<this.getColumns(); j++) {
                tmp[i][j] = this.getElement(i, j) + other.getElement(i, j);
            }
        }
        return new CompletedMatrixV2(tmp);
    }

    @Override
    public Matrix minus(Matrix other) {
        if(other==null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        if(this.getRows()!=other.getRows() || this.getColumns()!=other.getColumns()) {
            throw new RuntimeException("Matrices must have matching dimension.");
        }
        int[][] tmp = new int[this.getRows()][this.getColumns()];
        for(int i=0; i < this.getRows(); i++) {
            for(int j=0; j<this.getColumns(); j++) {
                tmp[i][j] = this.getElement(i, j) - other.getElement(i, j);
            }
        }
        return new CompletedMatrixV2(tmp);
    }

    @Override
    public Matrix multiply(Matrix other) {
        if(other==null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        if(this.getColumns()!=other.getRows()) {
            throw new RuntimeException("Matrice dimension mismatch.");
        }
        //create int[][] by multiplying other and this
        int[][] tmp = new int[this.getRows()][other.getColumns()];
        for(int i=0; i < this.getRows(); i++) {
            for(int j=0; j<other.getColumns(); j++) {
                for(int k=0; k<this.getColumns(); k++) {
                    tmp[i][j] += this.getElement(i, k) * other.getElement(k, j);
                }
            }

        }
        return new CompletedMatrixV2(tmp);
    }
    
    @Override
    public boolean equals(Object other) {
        if(other==null) return false;
        if(other.getClass()!=this.getClass()) return false;
        CompletedMatrixV2 otherMatrix = (CompletedMatrixV2) other;
        if(this.getColumns()!=otherMatrix.getColumns() || this.getRows()!=otherMatrix.getRows()){
            return false;
        }
        for(int i=0; i<this.getRows(); i++) {
            for(int j=0; j<this.getColumns(); j++) {
                if(this.getElement(i, j)!=otherMatrix.getElement(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String toString() {
        String result = "";
        for(int row = 0; row < this.getRows(); row++) {
            for(int column = 0; column < this.getColumns(); column++) {
                result += this.getElement(row, column);
                result += " ";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //These tests show sample usage of the matrix, and some basic ideas for testing. They are not comprehensive.

        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data5 = {{1, 4, 7}, {2, 5, 8}};

        Matrix m1 = new CompletedMatrixV2(data1);
        Matrix m2 = new CompletedMatrixV2(data2);
        Matrix m3 = new CompletedMatrixV2(data3);
        Matrix m4 = new CompletedMatrixV2(data4);
        Matrix m5 = new CompletedMatrixV2(data5);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true

        //test operations (valid)
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("m1 + m1:\n" + m1.plus(m1)); // typo having twice?
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("3 * m5:\n" + m5.scale(3));

        //not tested... multiply(). you know what to do.
        int[][] mult1 = {{1, 2, 3}, {4, 5, 6}};
        int[][] mult2 = {{10, 11}, {20, 21}, {30, 31}};
        Matrix multMat1 = new CompletedMatrixV2(mult1);
        Matrix multMat2 = new CompletedMatrixV2(mult2);
        System.out.println("mult1 * mult2:\n" + multMat1.multiply(multMat2));
        int[][] mult3 = {{1, 1, 1}, {1, 1, 1}};
        Matrix multMat3 = new CompletedMatrixV2(mult3);
        int[][] mult4 = {{0, 0}, {0, 0}, {0, 0}};
        Matrix multMat4 = new CompletedMatrixV2(mult4);
        System.out.println("mult3 * mult4:\n" + multMat3.multiply(multMat4));
        // test multiplying a 2x3 matrix with a 3x2 matrix
        int[][] mult5 = {{1, 2, 3}, {4, 5, 6}};
        int[][] mult6 = {{10, 11}, {20, 21}, {30, 31}};
        Matrix multMat5 = new CompletedMatrixV2(mult5);
        Matrix multMat6 = new CompletedMatrixV2(mult6);
        System.out.println("mult5 * mult6:\n" + multMat5.multiply(multMat6));
        // test multiplying a 3x2 matrix with a 2x3 matrix
        int[][] mult7 = {{1, 2}, {3, 4}, {5, 6}};
        int[][] mult8 = {{10, 11, 12}, {20, 21, 22}};
        Matrix multMat7 = new CompletedMatrixV2(mult7);
        Matrix multMat8 = new CompletedMatrixV2(mult8);
        System.out.println("mult7 * mult8:\n" + multMat7.multiply(multMat8));


        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 + m5" + m1.plus(m5));
        //System.out.println("m1 - m2" + m1.minus(m2));
        //System.out.println("multMat1 rows = " + multMat1.getRows() + " Columns: " + multMat1.getColumns());
        //System.out.println("multMat2 rows = " + multMat2.getRows() + " Columns: " + multMat2.getColumns());
    }
}