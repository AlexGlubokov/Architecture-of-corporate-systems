<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <head><style>table {border-collapse: collapse; width: 50%;} th, td {border: 1px solid black; padding: 8px;}</style></head>
  <body>
    <h2>Manufacturer List XSLT</h2>
    <table>
      <tr><th>ID</th><th>Name</th><th>Origin</th></tr>
      <xsl:for-each select="List/item">
        <tr>
          <td><xsl:value-of select="id"/></td>
          <td><xsl:value-of select="name"/></td>
          <td><xsl:value-of select="origin"/></td>
        </tr>
      </xsl:for-each>
    </table>
    <br/><a href="cars">Car</a>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
