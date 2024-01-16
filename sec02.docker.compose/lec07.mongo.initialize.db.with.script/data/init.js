db = db.getSiblingDB('product-service');

db.createCollection('products');

db.products.insertMany(
    [
        {
            "name": "iphone",
            "price": "1200.00"
        },
        {
            "name": "ipad",
            "price": "800.00"
        },
        {
            "name": "macbook",
            "price": "2000.00"
        }
    ]
)