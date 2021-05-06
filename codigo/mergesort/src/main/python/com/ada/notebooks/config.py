"""
Place in special paths and variables that are used across the project.
"""
import os
import sys
from pathlib import Path


project_dir = Path(__file__).parents[6]
data_dir = Path(os.path.join(project_dir, "src", "main", "resources", "com", "ada"))
