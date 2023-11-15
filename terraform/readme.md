## Initialize
### setup aws-vault
1. install [aws-vault](https://github.com/99designs/aws-vault)
2. execute `aws-vault add <profile>`
3. see `~/.aws/config`. add region and missing informations(accessKey, secretKey)

### initialize
1. run `aws-vault exec <profile> -- terraform init`