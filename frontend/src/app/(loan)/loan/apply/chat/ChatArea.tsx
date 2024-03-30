'use client';

import { Input } from '@/components';
import { PaperAirplaneIcon } from '@heroicons/react/16/solid';
import { useState, useEffect } from 'react';
import OpenAI from 'openai';
import ChatBox from '@/components/ChatBox/ChatBox';
import useLoanApplyStore from '../_store';
import { useRouter } from 'next/navigation';

type ChatMessage = {
  role: 'user' | 'assistant' | 'system';
  content: string;
};

export default function ChatArea() {
  const router = useRouter();
  const { apply, addConversation } = useLoanApplyStore();

  let string = '';
  const [input, setInput] = useState<any>();
  const [messages, setMessages] = useState<ChatMessage[]>([
    {
      role: 'system',
      content:
        '너는 아이들 대출 심사위원이야. 아이 대상이니까 말을 최대한 쉽게 해야돼. 돈의 목적에 대해선 묻지마. 이 내용은 아이의 부모님이 확인할 수 있어. 아이들이 대출하려는 이유가 타당한지, 대출 말고는 다른 방법은 없는지 등 아이들이 스스로 정말 대출이 타당한지 느낄 수 있도록 하는 역할을 해.그에 맞게 질문을 던져줘. 모든 답변은 질문 하나로만 해줘. 질문은 30자 이하로 하나씩만 해줘. 답변 세개 받으면 마무리로 대화가 끝났으니 부모님한테 보낼게라고 말해줘',
    },
    {
      role: 'user',
      content: `${apply?.reason}을 위해 ${apply?.amount}원을 빌릴꺼야. 
      ${apply?.paymentTotalCnt}개월동안 갚을게`,
    },
  ]);

  const key = process.env.NEXT_PUBLIC_GPT_API_KEY;

  const openai = new OpenAI({
    apiKey: key,
    dangerouslyAllowBrowser: true,
  });

  async function chatGPT() {
    const msg = input && [...messages, { role: 'user', content: input }];
    const stream = await openai.chat.completions.create(
      {
        model: 'gpt-4',
        messages: msg || messages,
        stream: true,
      },
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${process.env.NEXT_PUBLIC_GPT_API_KEY}`,
        },
      },
    );
    let res: string = '';
    for await (const chunk of stream) {
      chunk?.choices[0]?.delta?.content &&
        (res += chunk?.choices[0]?.delta?.content);
    }
    console.log(res);

    msg
      ? setMessages([...msg, { role: 'assistant', content: res }])
      : setMessages([...messages, { role: 'assistant', content: res }]);

    window.scrollTo(0, document.body.scrollHeight);
  }

  useEffect(() => {
    chatGPT();
  }, []);

  useEffect(() => {
    if (messages.length == 8) {
      messages.map((msg, index) => {
        if (index > 1) {
          string += `${msg.content}!#@#!`;
        }
      });
      addConversation(string);
    }
  }, [messages.length]);

  useEffect(() => {
    apply.conversation && router.replace('/loan/apply/check');
  }, [apply, router]);

  const sendMessage = async () => {
    setMessages([...messages, { role: 'user', content: input }]);
    await chatGPT();
    setInput('');
    window.scrollTo(0, document.body.scrollHeight);
  };

  return (
    <div>
      <div className="flex flex-col gap-[1rem] mb-[1rem]">
        {messages.map((msg, index) => {
          if (msg.role !== 'system' && index > 1) {
            return (
              <ChatBox
                key={`chat-${index}`}
                mode={msg.role === 'assistant' ? 'BOT' : 'USER'}
                message={msg.content}
              />
            );
          }
        })}
      </div>
      <div className="flex flex-row gap-[0.7rem] fixed bottom-0 w-full p-[1.2rem] pt-0 pr-[2.5rem] bg-custom-white border-none outline-none">
        <Input reset={false} value={input} setValue={setInput} />
        <PaperAirplaneIcon
          className="text-custom-purple w-[4rem] cursor-pointer"
          onClick={sendMessage}
        />
      </div>
    </div>
  );
}
