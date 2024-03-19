import type { Meta, StoryObj } from '@storybook/react';

import ChatBox from './ChatBox';

const meta = {
  title: 'Common/ChatBox',
  component: ChatBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: '나의 메시지(ME), 챗봇의 메시지(YOU) 타입이 있습니다.',
    },
    message: {
      description: '채팅내용입니다',
    },
    nickname: {
      description: '닉네임입니다.',
    },
  },
} satisfies Meta<typeof ChatBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const ME: Story = {
  args: {
    mode: 'ME',
    message: '안녕하세요. 로봇사려구요. 너무너무 필요해요',
    nickname: '쟈수민',
  },
};

export const YOU: Story = {
  args: {
    mode: 'YOU',
    message: '20,000이 필요한 이유가 뭔가요?',
  },
};
