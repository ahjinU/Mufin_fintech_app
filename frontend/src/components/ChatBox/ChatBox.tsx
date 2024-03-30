import Image from 'next/image';

interface ButtonProps {
  mode: 'USER' | 'BOT';
  message: string | React.ReactElement;
  nickname?: string;
}

export default function ChatBox({
  mode = 'BOT',
  message,
  nickname = '챗봇',
  ...props
}: ButtonProps) {
  let icon: string;
  switch (mode) {
    case 'USER':
      icon = '/images/icon-chat-me.png';
      break;
    case 'BOT':
      icon = '/images/icon-chat-bot.png';
      break;
    default:
      icon = '/images/icon-chat-me.png';
  }

  return (
    <div
      className={`w-full flex ${
        mode === 'USER' ? 'justify-end' : 'justify-start'
      } `}
    >
      <div
        className={`w-[25rem] flex flex-col ${
          mode === 'USER'
            ? 'bg-custom-light-purple p-[0.7rem]'
            : 'bg-custom-light-gray p-[1rem]'
        } rounded-[0.8rem] pb-0`}
      >
        <div
          className={`flex flex-row ${
            mode === 'BOT' && 'gap-[1rem]'
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
