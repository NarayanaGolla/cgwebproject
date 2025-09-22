variable "example_var" {
  description = "An example input variable"
  type        = string
  default     = "default_value"
}

resource "example_resource" "example" {
  name = var.example_var
  # other resource configurations
}