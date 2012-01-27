import java.util.ArrayList;


import Jama.Matrix;


public class NMF {

	public Matrix V;
	public Matrix W;
	public Matrix H;
	public Matrix VStrich;
	public int dim;
	public int patternCount;
	public int componentCount;
	
	
	
	public NMF(ArrayList<Pattern> pattern, int componentCount){
		this.dim = pattern.get(0).dim;
		this.patternCount = pattern.size();
		this.componentCount = componentCount;
		
		// Create arrays for matrices 
		double[][] arrayV = new double[dim][patternCount];
		double[][] arrayW = new double[dim][componentCount];
		double[][] arrayH = new double[componentCount][patternCount];
		
		// Fill matrix V with patterns
		for(int j=0; j<patternCount; j++){
			for(int i=0; i < dim; i++){
				// each column is a pattern, feature values are stored in the lines
				arrayV[i][j] = pattern.get(j).features.toArray()[i];
			}
		}
		
		// Fill matrices W and H with average values
		for(int i=0; i < dim; i++){
			for(int j=0; j<componentCount; j++){
				arrayW[i][j] = 1.0 / (dim*componentCount);
			}
		}
		for(int i=0; i < componentCount; i++){
			for(int j=0; j<patternCount; j++){
				arrayH[i][j] = 1.0 / (componentCount*patternCount);
			}
		}
		
		// Create matrixes out of arrays
		this.V = new Matrix(arrayV);
		this.W = new Matrix(arrayW);
		this.H = new Matrix(arrayH);
	}
	
	
	
	public void iterate(int iterations){
		for(int i=0; i<iterations; i++){
			calcVStrich();
			adjustingW();
			adjustingH();
		}
	}
	
	
	
	public void adjustingW(){
		for(int i=0; i<dim; i++){
			for(int a=0; a< componentCount; a++){
				double sum = 0;
				for(int myu=0; myu<patternCount; myu++){
					double tmp = V.getArray()[i][myu] / (VStrich.getArray()[i][myu]);
					sum += tmp * H.getArray()[a][myu];
				}
				double[][] newArray = W.getArray();
				newArray[i][a] = newArray[i][a] * sum;
				W = new Matrix(newArray);
			}
		}
		
		// normalize
		double sumW = 0.0;
		double[][] newArray = W.getArray();
		for(int a=0; a< componentCount; a++){
			sumW = 0.0;
			for(int j=0; j<dim; j++){
				sumW += W.getArray()[j][a];
			}
			for(int i=0; i<dim; i++){
				newArray[i][a] = newArray[i][a] / sumW;
			}
		}

//		// normalize by maximum w[i][a]
//		double sumW = 0.0;
//		double[][] newArray = W.getArray();
//		for(int a=0; a< componentCount; a++){
//			double max = 0.0;
//			for(int j=0; j<dim; j++){
//				if (W.getArray()[j][a] > max){
//					max = W.getArray()[j][a];
//				}
//			}
//			for(int i=0; i<dim; i++){
//				newArray[i][a] = newArray[i][a] / max;
//			}
//		}
		
		W = new Matrix(newArray);
		
		W = new Matrix(newArray);
	}
	

	
	public void adjustingH(){
		for(int myu=0; myu<patternCount; myu++){
			for(int a=0; a< componentCount; a++){
				// sum
				double sum = 0;
				for(int i=0; i<dim; i++){
					double tmp = V.getArray()[i][myu] / VStrich.getArray()[i][myu];
					sum += tmp * W.getArray()[i][a];
				}
				double[][] newArray = H.getArray();
				newArray[a][myu] = newArray[a][myu] * sum;
				H = new Matrix(newArray);
			}
		}
	}
	
	public ArrayList<Pattern> getVStrichPatterns(){
		ArrayList<Pattern> patterns = new ArrayList<Pattern>();
			for(int j=0; j<patternCount; j++){
				double[] features = new double[dim];
				for(int i=0; i < dim; i++){
					features[i] = VStrich.getArray()[i][j];
				}
				patterns.add(new Pattern(features));
			}
			return patterns;
		}	
	
	
	// Returns square error: ||v1 - v2||^2 
	public double distance(Matrix v1, Matrix v2){
		double dist = 0;
		for(int i=0; i<v1.getArray().length; i++){
			for(int j=0; j<v1.getArray()[i].length; j++){
				dist += Math.pow((v1.getArray()[i][j] - v2.getArray()[i][j]), 2);
			}
		}
		return dist;
	}

	

	// Returns D(V || V')
	public double kullbackLeibler(Matrix v1, Matrix v2){
		double dist = 0;
		for(int i=0; i<v1.getArray().length; i++){
			for(int j=0; j<v1.getArray()[i].length; j++){
				double tmpError = 0.0;
				tmpError += v1.getArray()[i][j];
				tmpError *= Math.log((double)v1.getArray()[i][j] / (double)v2.getArray()[i][j]);
				tmpError -= v1.getArray()[i][j];
				tmpError += v2.getArray()[i][j];
				dist += tmpError;
			}
		}
		return dist;
	}
	
	

	public void calcVStrich(){
		VStrich = W.times(H);
		//VStrich.plus ganz kleine Zahl
	}
	
	
	public ArrayList<Pattern> getWPatterns(){
		ArrayList<Pattern> patterns = new ArrayList<Pattern>();
		for(int j=0; j<componentCount; j++){
			double[] features = new double[dim];
			for(int i=0; i < dim; i++){
				features[i] = W.getArray()[i][j];
			}
			patterns.add(new Pattern(features));
		}
		return patterns;
	}
	
	
	
}
