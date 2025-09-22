output "example_output" {
  description = "An example output variable"
  value       = resource.example_resource.example.id
}

output "root_output" {
  value = module.example_module.example_output
}