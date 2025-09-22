provider "aws" {
    region = "ap-south-1"  # Set your desired AWS region
}

resource "aws_instance" "example" {
    ami           = "ami-0614680123427b75e"  # Specify an appropriate AMI ID
    instance_type = "t2.micro"
}