<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <head><style>table {border-collapse: collapse; width: 50%;} th, td {border: 1px solid black; padding: 8px;}</style></head>
  <body>
    <h2>Car List XSLT</h2>
    <table>
      <tr><th>ID</th><th>Model</th><th>Year</th><th>Color</th></tr>
      <xsl:for-each select="List/item">
        <tr>
          <td><xsl:value-of select="id"/></td>
          <td><xsl:value-of select="model"/></td>
          <td><xsl:value-of select="year"/></td>
          <td><xsl:value-of select="color"/></td>
        </tr>
      </xsl:for-each>
    </table>
    <br/><a href="manufacturers">Manufacturer</a>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
