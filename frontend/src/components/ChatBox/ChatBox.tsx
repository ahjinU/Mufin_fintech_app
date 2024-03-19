import Image from 'next/image';

interface ButtonProps {
  mode: 'ME' | 'YOU';
  message: string;
  nickname?: string;
}

export default function ChatBox({
  mode = 'YOU',
  message,
  nickname = '챗봇',
  ...props
}: ButtonProps) {
  let icon: string;
  switch (mode) {
    case 'ME':
      icon = 'http://localhost:3000/images/icon-chat-me.png';
      break;
    case 'YOU':
      icon = 'http://localhost:3000/images/icon-chat-bot.png';
      break;
    default:
      icon = 'http://localhost:3000/images/icon-chat-mepng';
  }

  return (
    <div
      className={`w-full flex ${
        mode === 'ME' ? 'justify-end' : 'justify-start'
      } `}
    >
      <div
        className={`w-[20rem] flex flex-col ${
          mode === 'ME'
            ? 'bg-custom-light-purple p-[0.7rem]'
            : 'bg-custom-light-gray p-[1rem]'
        } rounded-[0.8rem] pb-0`}
      >
        <div
          className={`flex flex-row ${
            mode === 'YOU' && 'gap-[1rem]'
          }  h-[3rem] items-center`}
        >
          <Image src={icon} width={35} height={10} alt="없음" />
          <p className="custom-semibold-text text-custom-black">{nickname}</p>
        </div>
        <p className="custom-light-text text-custom-black py-[1rem] px-[0.5rem] leading-[1.3rem]">
          {message}
        </p>
      </div>
    </div>
  );
}
