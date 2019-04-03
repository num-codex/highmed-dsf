package org.highmed.fhir.search.parameters.basic;

import java.util.Optional;

public class TokenValueAndSearchType
{
	public final String systemValue;
	public final String codeValue;
	public final TokenSearchType type;

	private TokenValueAndSearchType(String systemValue, String codeValue, TokenSearchType type)
	{
		this.systemValue = systemValue;
		this.codeValue = codeValue;
		this.type = type;
	}

	public static Optional<TokenValueAndSearchType> fromParamValue(String param)
	{
		if (param != null && !param.isBlank())
		{
			if (param.indexOf('|') == -1)
				return Optional.of(new TokenValueAndSearchType(null, param, TokenSearchType.CODE));
			else if (param.charAt(0) == '|')
				return Optional.of(new TokenValueAndSearchType(null, param.substring(1),
						TokenSearchType.CODE_AND_NO_SYSTEM_PROPERTY));
			else if (param.charAt(param.length() - 1) == '|')
				return Optional.of(new TokenValueAndSearchType(param.substring(0, param.length() - 1), null,
						TokenSearchType.SYSTEM));
			else
			{
				String[] splitAtPipe = param.split("[|]");
				return Optional.of(
						new TokenValueAndSearchType(splitAtPipe[0], splitAtPipe[1], TokenSearchType.CODE_AND_SYSTEM));
			}
		}
		else
			return Optional.empty();
	}
}