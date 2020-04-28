package github.aeharake.choters.mocker

import java.lang.IllegalStateException

class UsersGenerator {
    companion object {
        const val full_names =
            "Charles Hamilton,Faith Stewart,Diane McDonald,Olivia Sharp,Adrian Carr,Kylie Hudson,Simon Buckland,Grace Miller,Ruth Hunter,Virginia Welch,Jan Ogden,Carolyn Gray,Sean Coleman,Dominic Cornish,Ian Reid,Frank Terry,Michael Tucker,Jake Thomson,Katherine Mills,Sonia Greene,Megan Paige,Nicholas Rutherford,Ava Jones,Sue Short,Alexandra Walsh,Brandon Hughes,Penelope Lee,Evan Lambert,Benjamin May,Christian Walsh,Una White,Kevin Powell,Piers Poole,Oliver Bailey,Hannah Howard,Kimberly Nash,Emily Murray,Anne Abraham,Jacob Abraham,Wendy Thomson,Carolyn White,Neil Stewart,Jan Ross,Adrian Lambert,Jason Wilson,James Lambert,Rachel Hardacre,Alan Cornish,Colin Marshall,Stephen Randall,Tim Davidson,Justin Smith,Donna Metcalfe,Donna Lawrence,Stewart Knox,Natalie Kerr,Brandon Simpson,Wanda Wilkins,Nicholas MacDonald,Lucas Mackay,Gabrielle Parsons,Charles Harris,Donna Black,Gavin Arnold,Virginia Hill,Theresa Peters,Leah Miller,Sue Hart,Adrian Howard,Vanessa Welch,Amelia Nash,Gavin Henderson,Tim Coleman,Joan Mackenzie,Austin Robertson,Oliver Berry,Jake Bond,Phil Kelly,Trevor Peake,Joan Gray,Carl King,Steven Skinner,Steven Hill,Olivia Paige,Ian Berry,Blake Lambert,Jonathan Nash,Joan Rutherford,Trevor Thomson,Blake Marshall,Eric Ball,Carolyn Stewart,Liam Tucker,Sebastian Thomson,Max Vance,Justin Gill,Harry Hamilton,Isaac Harris,Rose Mathis,Lily Parsons,Harry Young,Anthony Dyer,Claire Scott,Sue Cornish,Rebecca Buckland,Warren Davidson,Sebastian Rutherford,Austin Ogden,Austin Berry,Oliver Taylor,Joe Mitchell,Owen Lyman,Lucas Howard,Jake Gibson,Jason Hart,Oliver Hill,Owen Cameron,Piers Lambert,Brian Scott,Deirdre Parr,Joshua Payne,Joe Simpson,Dorothy Harris,Samantha Hudson,Liam Gibson,Frank Butler,Molly Cornish,Natalie McLean,Hannah Kerr,Lily Mathis,Gabrielle Forsyth,Angela Greene,Rachel White,Jan Howard,Liam Arnold,Stephanie Robertson,Simon Stewart,Penelope Mills,Justin Parsons,Justin Nash,Gordon Paige,Dan Walsh,Victoria Manning,Joe McDonald,Keith White,Richard Clark,Victor Poole,Ava Dowd,Dominic Fisher,Richard Hughes,Natalie Carr,Stewart Gibson,Jasmine Turner,Bella Wilkins,Jake Mills,Alexandra Short,Jennifer McDonald,Carolyn Lambert,Joe Dickens,Lily Watson,Fiona McGrath,Andrea Reid,Blake Bower,Deirdre Nash,Katherine Terry,Elizabeth Poole,Sam Hemmings,Katherine Reid,Kylie Bailey,Harry Roberts,Sue Bower,Boris Oliver,Donna Lewis,Audrey Wright,Sebastian Avery,Ruth Mackenzie,Carol Brown,Paul Fraser,Theresa Avery,Steven Hodges,Alan Baker,Joe Skinner,Melanie Blake,Anthony Campbell,Penelope Rampling,Gabrielle Alsop,Andrea King,Austin Bell,Ian Edmunds,Edward Cameron,Stephanie Brown,Benjamin Dyer,Michelle Paige,Frank Sanderson,Luke Jones,Adam Lee,Karen Chapman,Diana Lambert,Anna Bell,Carolyn Mackay"

        private fun getFullNames(): List<String> {
            return full_names.split(",")
        }

        private fun shuffleFirstNames(): List<String> {
            return getFullNames()
                .map {
                it.split(" ")[0]
            }.shuffled().toList()
        }


        private fun shuffleLastNames(): List<String> {
            //it's ok to call this again as this will not be executed in our app when we already have the 200 names stored
            return getFullNames()
                .map {
                it.split(" ")[1]
            }.shuffled().toList()
        }

        fun getRandomFullNames(): List<FullName> {
            val shuffledFirstNames =
                shuffleFirstNames()
            val shuffledLastNames =
                shuffleLastNames()
            if (shuffledFirstNames.size != shuffledLastNames.size) {
                throw IllegalStateException("Different sizes for first names and last name and unable to mock");
            }
            return mutableListOf<FullName>().apply {
                var i = 0
                while (i < 200) {
                    add(
                        FullName(
                            shuffledFirstNames[i],
                            shuffledLastNames[i]
                        )
                    )
                    i++;
                }
            }
        }
    }
}
data class FullName(val firstName: String, val lastName: String);