# Text similarity. 

Webapp structure imported from allenai/templates/webapp

If you need to add a similarity service:

1. Implement the org.allenai.similarity.implementations.Similarity trait in core.

2. Add your service to org.allenai.similarity.services.ProductionSimilarityServiceComponent following the BOW example.

To run webapp:

1. sbt

2. webserver/reStart

