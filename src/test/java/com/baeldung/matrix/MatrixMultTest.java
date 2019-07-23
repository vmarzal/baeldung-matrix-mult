package com.baeldung.matrix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class MatrixMultTest {
	 	
	@Test
	void multiplyMatrices() {
		double[][] firstMatrix = new double[][] { new double[] { 1d, 5d }, new double[] { 2d, 3d },
				new double[] { 1d, 7d } };

		double[][] secondMatrix = new double[][] { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } };

		double[][] expected = new double[][] { new double[] { 26d, 12d, 43d, 12d }, new double[] { 17d, 10d, 30d, 17d },
				new double[] { 36d, 16d, 59d, 14d } };
						
		double[][] calculated = new MatrixMult().multiplyMatrices(firstMatrix, secondMatrix);		
		assertArrayEquals(expected, calculated);
	}
	
	@Test
	void multiplyMatricesWithEJML() {
		SimpleMatrix firstMatrix = new SimpleMatrix(
				new double[][] { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } });
		
		SimpleMatrix secondMatrix = new SimpleMatrix(
				new double[][] { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } });

		SimpleMatrix expected = new SimpleMatrix(new double[][] { new double[] { 26d, 12d, 43d, 12d },
				new double[] { 17d, 10d, 30d, 17d }, new double[] { 36d, 16d, 59d, 14d } });
		
		SimpleMatrix actual = firstMatrix.mult(secondMatrix);		
		assertThat(actual).matches(m -> m.isIdentical(expected, 0d));		
	}
	
	@Test
	void multiplyMatricesWithND4J() {
		INDArray firstMatrix = Nd4j
				.create(new double[][] { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } });

		INDArray secondMatrix = Nd4j
				.create(new double[][] { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } });
		
		INDArray expected = Nd4j.create(new double[][] { new double[] { 26d, 12d, 43d, 12d },
				new double[] { 17d, 10d, 30d, 17d }, new double[] { 36d, 16d, 59d, 14d } });
		
		INDArray actual = firstMatrix.mmul(secondMatrix);						
		assertThat(actual).isEqualTo(expected);
		
	}
	
	@Test
	void multiplyMatricesApacheCommons() {
		RealMatrix firstMatrix = new Array2DRowRealMatrix(							
				new double[][] { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } });
		
		RealMatrix secondMatrix = new Array2DRowRealMatrix(
				new double[][] { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } });
		
		RealMatrix expected = new Array2DRowRealMatrix(new double[][] { new double[] { 26d, 12d, 43d, 12d },
				new double[] { 17d, 10d, 30d, 17d }, new double[] { 36d, 16d, 59d, 14d } });
		
		RealMatrix actual = firstMatrix.multiply(secondMatrix);
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void multiplyMatricesLA4J() {
		Matrix firstMatrix = new Basic2DMatrix(
				new double[][] { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } });
		
		Matrix secondMatrix = new Basic2DMatrix(
				new double[][] { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } });
		
		Matrix expected = new Basic2DMatrix(new double[][] { new double[] { 26d, 12d, 43d, 12d },
				new double[] { 17d, 10d, 30d, 17d }, new double[] { 36d, 16d, 59d, 14d } });
		
		Matrix actual = firstMatrix.multiply(secondMatrix);
		assertThat(actual).isEqualTo(expected);
		
	}
	
	@Test
	void multiplyMatricesColt() {
		DoubleMatrix2D firstMatrix = DoubleFactory2D.dense							
				.make(new double[][] { new double[] { 1d, 5d }, new double[] { 2d, 3d }, new double[] { 1d, 7d } });
		
		DoubleMatrix2D secondMatrix = DoubleFactory2D.dense							
				.make(new double[][] { new double[] { 1d, 2d, 3d, 7d }, new double[] { 5d, 2d, 8d, 1d } });
		
		DoubleMatrix2D expected = DoubleFactory2D.dense.make(new double[][] { new double[] { 26d, 12d, 43d, 12d },
				new double[] { 17d, 10d, 30d, 17d }, new double[] { 36d, 16d, 59d, 14d } });
		
		Algebra algebra = new Algebra();
		DoubleMatrix2D actual = algebra.mult(firstMatrix, secondMatrix);
		assertThat(actual).isEqualTo(expected);		
	}
	
}
