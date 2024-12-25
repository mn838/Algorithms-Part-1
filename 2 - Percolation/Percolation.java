/* *****************************************************************************
 *  Name:              Mahi Nuthanapati
 *  Coursera User ID:  Mahi Nuthanapati
 *  Last modified:     December 24th, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    private WeightedQuickUnionUF wuf;
    private WeightedQuickUnionUF supportWuf;
    private int gridSize;
    private int virtualTop;
    private int virtualBottom;
    private int numOpenSites;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n needs to be greater than 0");
        this.grid = new boolean[n][n];
        this.wuf = new WeightedQuickUnionUF((n * n) + 2);
        this.supportWuf = new WeightedQuickUnionUF((n * n) + 1);
        this.gridSize = n;
        this.virtualTop = 0;
        this.virtualBottom = (n * n) + 1;
        this.numOpenSites = 0;
    }

    private boolean validateSite(int row, int col) {
        return (row >= 1 && row <= this.gridSize) && (col >= 1 && col <= this.gridSize);
    }

    private int flattenIndex(int row, int col) {
        return ((row - 1) * this.gridSize) + col;
    }

    public void open(int row, int col) {
        if (!validateSite(row, col)) throw new IllegalArgumentException("Invalid Site");

        if (isOpen(row, col)) {
            return;
        }

        int flatIndex = flattenIndex(row, col);
        grid[row - 1][col - 1] = true;
        this.numOpenSites++;

        if (row == 1) {
            this.wuf.union(this.virtualTop, flatIndex);
            this.supportWuf.union(this.virtualTop, flatIndex);
        }
        if (row == this.gridSize) {
            this.wuf.union(this.virtualBottom, flatIndex);
        }

        if (validateSite(row, col - 1) && isOpen(row, col - 1)) {
            this.wuf.union(flatIndex, flattenIndex(row, col - 1));
            this.supportWuf.union(flatIndex, flattenIndex(row, col - 1));
        }
        if (validateSite(row, col + 1) && isOpen(row, col + 1)) {
            this.wuf.union(flatIndex, flattenIndex(row, col + 1));
            this.supportWuf.union(flatIndex, flattenIndex(row, col + 1));
        }
        if (validateSite(row - 1, col) && isOpen(row - 1, col)) {
            this.wuf.union(flatIndex, flattenIndex(row - 1, col));
            this.supportWuf.union(flatIndex, flattenIndex(row - 1, col));
        }
        if (validateSite(row + 1, col) && isOpen(row + 1, col)) {
            this.wuf.union(flatIndex, flattenIndex(row + 1, col));
            this.supportWuf.union(flatIndex, flattenIndex(row + 1, col));
        }
    }

    public boolean isOpen(int row, int col) {
        if (!validateSite(row, col)) throw new IllegalArgumentException("Invalid Index");
        return this.grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!validateSite(row, col)) throw new IllegalArgumentException("Invalid Index");
        return this.supportWuf.find(this.virtualTop) == this.supportWuf.find(
                flattenIndex(row, col));
    }

    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    public boolean percolates() {
        return this.wuf.find(this.virtualTop) == this.wuf.find(this.virtualBottom);
    }

    public static void main(String[] args) {
    }
}
