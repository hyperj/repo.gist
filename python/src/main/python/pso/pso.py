import numpy as np
import pyswarms as ps
from pyswarms.utils.functions import single_obj as fx

# Set-up hyperparameters
min_bound = np.ones(2)
max_bound = min_bound * 2
bounds = (min_bound, max_bound)
options = {'c1': 0.5, 'c2': 0.3, 'w': 0.9}
# Call instance of PSO
optimizer = ps.single.GlobalBestPSO(n_particles=10, dimensions=2, options=options, bounds=bounds)
# Perform optimization
best_cost, best_pos = optimizer.optimize(fx.sphere, iters=1000)
